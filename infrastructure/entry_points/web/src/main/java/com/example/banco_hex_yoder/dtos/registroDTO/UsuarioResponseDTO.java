package com.example.banco_hex_yoder.dtos.registroDTO;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioResponseDTO {

    private Integer id;
    private String username;
    private String customerName;
    private Integer customerId;
    private List<String> roles;
    private LocalDateTime createdAt;
    private boolean isDeleted;
    private String password;

    public UsuarioResponseDTO(Integer id, String username, String customerName, List<String> roles, LocalDateTime createdAt, boolean isDeleted, Integer customerId, String password) {
        this.id = id;
        this.username = username;
        this.customerName = customerName;
        this.roles = roles;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.customerId = customerId;
        this.password = password;
    }

    public UsuarioResponseDTO() {}


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
