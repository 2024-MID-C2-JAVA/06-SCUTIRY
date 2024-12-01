package com.example.banco_hex_yoder.handlers.usuarios;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioResponseDTO;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.UserDocument;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.UserMongoRepository;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObtenerUsuariosHandler {

    private final UserMongoRepository userRepository;
    private final CustomerMongoRepository customerRepository;

    public ObtenerUsuariosHandler(UserMongoRepository userRepository, CustomerMongoRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public DinResponse<List<UsuarioResponseDTO>> obtenerUsuarios() {
        List<UserDocument> usuarios = userRepository.findAll();
        List<UsuarioResponseDTO> respuesta = usuarios.stream().map(usuario -> {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setUsername(usuario.getUsername());
            dto.setPassword(usuario.getPassword()); // Incluir la contraseña encriptada
            dto.setRoles(usuario.getRoles());
            dto.setCustomerId(usuario.getCustomerId());
            dto.setCreatedAt(usuario.getCreatedAt());
            dto.setDeleted(usuario.isDeleted());

            Optional<ClienteDocument> cliente = customerRepository.findById(usuario.getCustomerId());
            dto.setCustomerName(cliente.map(ClienteDocument::getUsername).orElse("Desconocido"));

            return dto;
        }).collect(Collectors.toList());

        DinResponse<List<UsuarioResponseDTO>> response = new DinResponse<>();
        response.setDinBody(respuesta);
        response.setDinError(new DinError("N", "0000", "Listado de usuarios obtenido exitosamente", "Operación completada sin errores"));
        return response;
    }
}
