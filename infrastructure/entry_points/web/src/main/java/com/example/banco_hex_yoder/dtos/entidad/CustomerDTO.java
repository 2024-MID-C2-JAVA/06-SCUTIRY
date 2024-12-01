package com.example.banco_hex_yoder.dtos.entidad;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

public class CustomerDTO {

    private Integer id;
    private String username;

    @JsonManagedReference
    private List<AccountDTO> accounts;



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

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
