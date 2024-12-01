package com.example.banco_hex_yoder.config.exceptions;

import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.common.exceptions.AccountNotFoundException;
import com.example.banco_hex_yoder.common.exceptions.InsufficientFundsException;
import com.example.banco_hex_yoder.common.exceptions.EncryptionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<DinResponse<Object>> handleAccountNotFoundException(AccountNotFoundException ex) {
        DinError error = new DinError("WARNING", "1002", "The account does not exist", ex.getMessage());
        DinResponse<Object> response = new DinResponse<>();
        response.setDinError(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<DinResponse<Object>> handleInsufficientFundsException(InsufficientFundsException ex) {
        DinError error = new DinError("WARNING", "1003", "Insufficient funds", ex.getMessage());
        DinResponse<Object> response = new DinResponse<>();
        response.setDinError(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EncryptionException.class)
    public ResponseEntity<DinResponse<Object>> handleEncryptionException(EncryptionException ex) {
        DinError error = new DinError("ERROR", "3006", "Error encrypting data", ex.getMessage());
        DinResponse<Object> response = new DinResponse<>();
        response.setDinError(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DinResponse<Object>> handleGeneralException(Exception ex) {
        DinError error = new DinError("ERROR", "1006", "Unknown error", ex.getMessage());
        DinResponse<Object> response = new DinResponse<>();
        response.setDinError(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
