package com.example.banco_hex_yoder.dtos.loginDTO;

import com.example.banco_hex_yoder.dtos.accountDTO.AccountResponseDTO;

import java.util.List;

public class AuthenticationResponseDTO {
    private String token;
    private List<String> roles;
    private List<AccountResponseDTO> cuentas; // Agregado
    private String username; // Agregado

    public AuthenticationResponseDTO() {}

    public AuthenticationResponseDTO(String token, List<String> roles, List<AccountResponseDTO> cuentas, String username) {
        this.token = token;
        this.roles = roles;
        this.cuentas = cuentas;
        this.username = username;
    }

    public AuthenticationResponseDTO(String token, List<String> roles, String username) {
        this.token = token;
        this.roles = roles;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<AccountResponseDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<AccountResponseDTO> cuentas) {
        this.cuentas = cuentas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
