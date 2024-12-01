package co.sofka.data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AccountTransactionId implements Serializable {

    private int accountId;
    private int transactionId;

    public AccountTransactionId() {
    }

    public AccountTransactionId(int accountId, int transactionId) {
        this.accountId = accountId;
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTransactionId)) return false;
        AccountTransactionId that = (AccountTransactionId) o;
        return accountId == that.accountId && transactionId == that.transactionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactionId);
    }
}
