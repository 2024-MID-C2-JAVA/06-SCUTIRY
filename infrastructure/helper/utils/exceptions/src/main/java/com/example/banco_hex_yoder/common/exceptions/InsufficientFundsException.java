package com.example.banco_hex_yoder.common.exceptions;



public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
