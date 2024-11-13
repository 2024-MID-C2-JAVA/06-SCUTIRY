package co.sofka.data;

import co.sofka.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "account_transaction")
public class AccountTransactionEntity {

    @EmbeddedId
    private AccountTransactionId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

    private String role;

    public AccountTransactionEntity() {
        this.id=new AccountTransactionId();
        this.role =String.valueOf(Role.PAYROLL);
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AccountTransactionId getId() {
        return id;
    }

    public void setId(AccountTransactionId id) {
        this.id = id;
    }
}
