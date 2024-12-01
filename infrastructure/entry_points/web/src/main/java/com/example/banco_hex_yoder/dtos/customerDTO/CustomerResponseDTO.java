package com.example.banco_hex_yoder.dtos.customerDTO;

import java.time.LocalDateTime;

public class CustomerResponseDTO {
    private Integer id;
    private String username;
    private LocalDateTime createdAt;
    private boolean isDeleted;


    public CustomerResponseDTO(Integer id, String username, LocalDateTime createdAt, boolean isDeleted) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime ccc() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
