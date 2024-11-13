package co.sofka.data.transaction;

import co.sofka.enums.TypeOfTransaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransactionDto {

    private String id;
    private String accountId;
    private BigDecimal amount;
    private BigDecimal amountCost;
    private TypeOfTransaction type;
    private OffsetDateTime timeStamp;


    public TransactionDto(String id, String accountId, BigDecimal amount, BigDecimal amountCost, TypeOfTransaction type, OffsetDateTime timeStamp) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.amountCost = amountCost;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public TransactionDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}

