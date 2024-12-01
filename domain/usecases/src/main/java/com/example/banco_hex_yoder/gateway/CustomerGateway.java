package com.example.banco_hex_yoder.gateway;

import com.example.banco_hex_yoder.model.Customer;

import java.util.Optional;

public interface CustomerGateway {
    Optional<Customer> findById(Integer id);
}
