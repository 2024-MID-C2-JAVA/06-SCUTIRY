package com.example.banco_hex_yoder.postgresql_repository.data.entidades;

import com.example.banco_hex_yoder.postgresql_repository.data.compositekeys.TransactionAccountDetailId;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction_account_detail")
public class TransactionAccountDetailEntity implements Serializable {

    @EmbeddedId
    private TransactionAccountDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    private TransaccionEntity transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private CuentaEntity account;

    @Column(name = "transaction_role")
    private String transactionRole;

  
    public TransactionAccountDetailEntity() {}


    public TransactionAccountDetailEntity(TransaccionEntity transaction, CuentaEntity account, String transactionRole) {
        this.id = new TransactionAccountDetailId(transaction.getId(), account.getId());
        this.transaction = transaction;
        this.account = account;
        this.transactionRole = transactionRole;
    }


    public TransactionAccountDetailId getId() {
        return id;
    }

    public void setId(TransactionAccountDetailId id) {
        this.id = id;
    }

    public TransaccionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransaccionEntity transaction) {
        this.transaction = transaction;
        if (this.id == null) {
            this.id = new TransactionAccountDetailId();
        }
        this.id.setTransactionId(transaction.getId());
    }

    public CuentaEntity getAccount() {
        return account;
    }

    public void setAccount(CuentaEntity account) {
        this.account = account;
        if (this.id == null) {
            this.id = new TransactionAccountDetailId();
        }
        this.id.setAccountId(account.getId());
    }

    public String getTransactionRole() {
        return transactionRole;
    }

    public void setTransactionRole(String transactionRole) {
        this.transactionRole = transactionRole;
    }
}
