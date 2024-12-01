package com.example.banco_hex_yoder.dtos.entidad;



import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;

public class AccountDTO {

    private Integer id;
    private Integer number;
    private BigDecimal amount;

    @JsonBackReference
    private CustomerDTO customer;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
