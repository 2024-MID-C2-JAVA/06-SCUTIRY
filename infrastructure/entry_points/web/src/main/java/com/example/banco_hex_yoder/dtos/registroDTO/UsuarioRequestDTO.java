package com.example.banco_hex_yoder.dtos.registroDTO;

import java.util.List;

public class UsuarioRequestDTO {

    private String username;
    private String password;
    private List<String> roles;
    private Integer customerId;

    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(String username, String password, List<String> roles, Integer customerId) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
