package co.sofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "bank_db")
public class CustomerDocument {

    @Id
    private String id;
    private String name;
    @Field(name = "created_at")
    private LocalDate createdAt;
    @Field(name = "is_deleted")
    private Boolean isDeleted;
    @Field(name = "account_customer")
    private AccountDocument account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AccountDocument getAccount() {
        return account;
    }

    public void setAccount(AccountDocument account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "CustomerDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                ", account=" + account +
                '}';
    }
}
