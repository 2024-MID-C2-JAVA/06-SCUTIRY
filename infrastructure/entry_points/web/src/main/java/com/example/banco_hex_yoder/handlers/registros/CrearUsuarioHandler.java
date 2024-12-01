package com.example.banco_hex_yoder.handlers.registros;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioRequestDTO;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioResponseDTO;
import com.example.banco_hex_yoder.encriptacion.EncripcionService;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.UserDocument;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.UserMongoRepository;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CrearUsuarioHandler {

    private final UserMongoRepository userRepository;
    private final CustomerMongoRepository customerRepository;
    private final EncripcionService encripcionService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CrearUsuarioHandler(UserMongoRepository userRepository,
                               CustomerMongoRepository customerRepository,
                               EncripcionService encripcionService,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.encripcionService = encripcionService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public DinResponse<UsuarioResponseDTO> crearUsuario(DinRequest<UsuarioRequestDTO> request) {
        DinResponse<UsuarioResponseDTO> response = new DinResponse<>();
        response.setDinHeader(request.getDinHeader());

        try {
            String username = request.getDinBody().getUsername();

            if (userRepository.findByUsername(username).isPresent()) {
                response.setDinError(new DinError("ERROR", "1003", "Nombre de usuario en uso", "El nombre de usuario ya existe en el sistema."));
                return response;
            }

            String symmetricKey = request.getDinHeader().getLlaveSimetrica();
            String initializationVector = request.getDinHeader().getVectorInicializacion();

            Optional<ClienteDocument> customerOpt = customerRepository.findById(request.getDinBody().getCustomerId());
            if (customerOpt.isEmpty()) {
                response.setDinError(new DinError("ERROR", "1002", "Cliente no encontrado", "No existe el cliente con el ID especificado."));
                return response;
            }

            String encryptedPassword = bCryptPasswordEncoder.encode(request.getDinBody().getPassword());

            Integer nextId = getNextUserId();

            UserDocument newUser = new UserDocument();
            newUser.setId(nextId);
            newUser.setUsername(username);
            newUser.setPassword(encryptedPassword);
            newUser.setRoles(request.getDinBody().getRoles());
            newUser.setCustomerId(request.getDinBody().getCustomerId());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setDeleted(false);

            UserDocument savedUser = userRepository.save(newUser);

            UsuarioResponseDTO responseBody = new UsuarioResponseDTO(
                    savedUser.getId(),
                    savedUser.getUsername(),
                    customerOpt.get().getUsername(),
                    savedUser.getRoles(),
                    savedUser.getCreatedAt(),
                    savedUser.isDeleted(),
                    savedUser.getCustomerId(),
                    savedUser.getPassword()  // Agregar el password encriptado aquí
            );
            response.setDinBody(responseBody);
            response.setDinError(new DinError("N", "0000", "Usuario creado exitosamente", "El usuario ha sido registrado con éxito."));

        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
        }

        return response;
    }

    private Integer getNextUserId() {
        Optional<UserDocument> maxUser = userRepository.findTopByOrderByIdDesc();
        return maxUser.map(user -> user.getId() + 1).orElse(1);
    }
}
