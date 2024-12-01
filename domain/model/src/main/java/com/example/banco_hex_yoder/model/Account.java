package com.example.banco_hex_yoder.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private Integer id;
    private Integer number;
    private BigDecimal amount;
    private LocalDate createdAt;
    private boolean isDeleted;
    private Integer customerId;



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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Boolean getDeleted() { // Getter para isDeleted
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) { // Setter para isDeleted
        isDeleted = deleted;
    }
}
