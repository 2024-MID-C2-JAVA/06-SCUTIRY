package com.example.banco_hex_yoder.rest.clientes;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.customerDTO.CustomerResponseDTO;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "customers", description = "Obtener customers")
public class CustomerController {

    @Autowired
    private CustomerMongoRepository customerRepository;

    @PostMapping
    public DinResponse<List<CustomerResponseDTO>> getAllActiveCustomers() {
        DinResponse<List<CustomerResponseDTO>> response = new DinResponse<>();

        try {
            List<ClienteDocument> customers = customerRepository.findAll()
                    .stream()
                    .filter(cliente -> !cliente.isDeleted())
                    .collect(Collectors.toList());

            List<CustomerResponseDTO> customerDTOs = customers.stream()
                    .map(cliente -> new CustomerResponseDTO(
                            cliente.getId(),
                            cliente.getUsername(),
                            cliente.getCreatedAt(),  // Asegúrate de que este campo esté asignado en ClienteDocument
                            cliente.isDeleted()
                    ))
                    .collect(Collectors.toList());

            response.setDinBody(customerDTOs);
            response.setDinError(new DinError("N", "0000", "Listado de clientes obtenido exitosamente", "Operación completada sin errores"));

        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
        }

        return response;
    }
}
