package com.example.banco_hex_yoder.handlers.customers;

import com.example.banco_hex_yoder.dtos.customerDTO.CustomerResponseDTO;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerHandler {

    private final CustomerMongoRepository customerRepository;

    public CustomerHandler(CustomerMongoRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public DinResponse<List<CustomerResponseDTO>> obtenerTodosLosClientes() {
        DinResponse<List<CustomerResponseDTO>> response = new DinResponse<>();

        try {

            List<CustomerResponseDTO> clientes = customerRepository.findAll()
                    .stream()
                    .map(cliente -> new CustomerResponseDTO(
                            cliente.getId(),
                            cliente.getUsername(),
                            cliente.getCreatedAt(),
                            cliente.isDeleted()
                    ))
                    .collect(Collectors.toList());

            response.setDinBody(clientes);
            response.setDinError(new DinError("N", "0000", "Consulta exitosa", "Clientes obtenidos correctamente."));
        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error al obtener clientes", e.getMessage()));
        }

        return response;
    }
}
