package co.sofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "bank_db")
public class AccountDocument {

    @Id
    private String id;
    private int number;
    private BigDecimal amount;
    @Field(name = "created_at")
    private LocalDate createdAt;
    @Field(name = "is_deleted")
    private Boolean isDeleted;
    @Field(name = "account_transactions")
    private List<TransactionDocument> transactions;
    @Field(name = "customer_id")
    private String customerId;

    public AccountDocument() {
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<TransactionDocument> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDocument> transactions) {
        this.transactions = transactions;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
