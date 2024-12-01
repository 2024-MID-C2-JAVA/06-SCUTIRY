package co.sofka.data.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDto {

    private String id;
    private String number;
    private BigDecimal amount;
    private String customerId;
    private LocalDate createdAt;

    public AccountDto(String id,String number, BigDecimal amount, String customerId, LocalDate createdAt) {
        this.id=id;
        this.number = number;
        this.amount = amount;
        this.customerId = customerId;
        this.createdAt = createdAt;
    }

    public AccountDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
