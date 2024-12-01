package com.example.banco_hex_yoder.rest.jwt;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.customerDTO.CustomerResponseDTO;
import com.example.banco_hex_yoder.dtos.loginDTO.AuthenticationResponseDTO;
import com.example.banco_hex_yoder.dtos.accountDTO.AccountResponseDTO;
import com.example.banco_hex_yoder.dtos.loginDTO.LoginRequestDTO;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.AccountMongoRepository;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import com.example.banco_hex_yoder.security_jwt.JwtUtil;
import com.example.banco_hex_yoder.security_jwt.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper; // Importa ObjectMapper
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "Operaciones relacionadas con auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AccountMongoRepository accountMongoRepository;

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @Autowired
    private ObjectMapper objectMapper; // Inyecta ObjectMapper

    @Operation(summary = "login", description = "genera un jwt")
    @PostMapping("/login")
    public DinResponse<Object> login(@RequestBody DinRequest<LoginRequestDTO> request) {
        DinResponse<Object> response = new DinResponse<>();
        response.setDinHeader(request.getDinHeader());

        String username = request.getDinBody().getUsername();
        String password = request.getDinBody().getPassword();

        try {
            // Authenticate user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Generate token
            String token = jwtUtil.generarToken(userDetails);

            // Extract roles
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                    .collect(Collectors.toList());

            // Get customerId from customer collection
            Integer customerId = accountMongoRepository.findCustomerIdByUsername(username);

            List<AccountResponseDTO> cuentas = null;

            if (customerId != null) {
                // Fetch accounts associated with the customerId
                cuentas = accountMongoRepository.findAll().stream()
                        .filter(account -> account.getCustomerId().equals(customerId))
                        .map(account -> {
                            AccountResponseDTO dto = new AccountResponseDTO();
                            dto.setId(account.getId());
                            dto.setNumber(account.getNumber());
                            dto.setAmount(account.getAmount());
                            dto.setCustomerId(account.getCustomerId());
                            dto.setCreatedAt(account.getCreatedAt());
                            dto.setDeleted(account.isDeleted());

                            // Fetch customer details and convert to JSON string
                            customerMongoRepository.findById(account.getCustomerId())
                                    .ifPresent(customer -> {
                                        try {
                                            CustomerResponseDTO customerDto = new CustomerResponseDTO(
                                                    customer.getId(),
                                                    customer.getUsername(),
                                                    customer.getCreatedAt(),
                                                    customer.isDeleted()
                                            );
                                            String customerJson = objectMapper.writeValueAsString(customerDto); // Convert to JSON string
                                            dto.setCustomer(customerJson); // Asignar como String
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    });

                            return dto;
                        }).collect(Collectors.toList());
            }

            // Prepare response
            AuthenticationResponseDTO responseBody = new AuthenticationResponseDTO(token, roles, cuentas, username);
            response.setDinBody(responseBody);

            response.setDinError(new DinError("N", "0000", "Login exitoso", "Autenticación realizada correctamente"));

        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error en la autenticación", e.getMessage()));
        }

        return response;
    }
}
