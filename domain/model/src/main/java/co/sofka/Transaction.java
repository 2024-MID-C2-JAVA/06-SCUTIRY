package co.sofka;

import co.sofka.enums.Role;
import co.sofka.enums.TypeOfTransaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {

    private String id;
    private BigDecimal amount;
    private BigDecimal amountCost;
    private TypeOfTransaction type;
    private OffsetDateTime timeStamp;
    private String accountId;
    private Role role;

    public Transaction(String id, BigDecimal amount, BigDecimal amountCost, TypeOfTransaction type, OffsetDateTime timeStamp) {
        this.id = id;
        this.amount = amount;
        this.amountCost = amountCost;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public Transaction(){}

    public Transaction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountCost() {
        return amountCost;
    }

    public void setAmountCost(BigDecimal amountCost) {
        this.amountCost = amountCost;
    }

    public TypeOfTransaction getType() {
        return type;
    }

    public void setType(TypeOfTransaction type) {
        this.type = type;
    }

    public OffsetDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(OffsetDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", amountCost=" + amountCost +
                ", type=" + type +
                ", timeStamp=" + timeStamp +
                ", accountId='" + accountId + '\'' +
                ", role=" + role +
                '}';
    }
}
