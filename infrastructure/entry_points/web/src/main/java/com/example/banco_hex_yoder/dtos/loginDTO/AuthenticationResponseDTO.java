package com.example.banco_hex_yoder.dtos.loginDTO;

import java.util.List;

public class AuthenticationResponseDTO {
    private String token;
    private List<String> roles;

    public AuthenticationResponseDTO() {}

    public AuthenticationResponseDTO(String token, List<String> roles) {
        this.token = token;
        this.roles = roles;
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
}
