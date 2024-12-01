 package com.example.banco_hex_yoder.common.exceptions;



public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
