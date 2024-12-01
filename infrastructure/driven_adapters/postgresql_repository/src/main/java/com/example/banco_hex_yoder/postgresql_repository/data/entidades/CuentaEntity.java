package com.example.banco_hex_yoder.postgresql_repository.data.entidades;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "account")
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private ClienteEntity customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionAccountDetailEntity> transactionDetails;


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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ClienteEntity getCustomer() {
        return customer;
    }

    public void setCustomer(ClienteEntity customer) {
        this.customer = customer;
    }

    public List<TransactionAccountDetailEntity> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionAccountDetailEntity> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
