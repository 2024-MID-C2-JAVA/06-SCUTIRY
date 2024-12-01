package com.example.banco_hex_yoder.postgresql_repository.data.mappers;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.model.Transaction;
import com.example.banco_hex_yoder.postgresql_repository.data.entidades.CuentaEntity;
import com.example.banco_hex_yoder.postgresql_repository.data.entidades.TransaccionEntity;

public class EntityMapper {


    public static Account toModel(CuentaEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setNumber(entity.getNumber());
        account.setAmount(entity.getAmount());
        account.setCreatedAt(entity.getCreatedAt());

        return account;
    }

    public static CuentaEntity toEntity(Account model) {
        CuentaEntity entity = new CuentaEntity();
        entity.setId(model.getId());
        entity.setNumber(model.getNumber());
        entity.setAmount(model.getAmount());
        entity.setCreatedAt(model.getCreatedAt());

        return entity;
    }


    public static Transaction toModel(TransaccionEntity entity) {
        Transaction transaction = new Transaction();
        transaction.setId(entity.getId());
        transaction.setAmountTransaction(entity.getAmountTransaction());
        transaction.setTransactionCost(entity.getTransactionCost());
        transaction.setTypeTransaction(entity.getTypeTransaction());
        transaction.setTimeStamp(entity.getTimeStamp());
        return transaction;
    }

    public static TransaccionEntity toEntity(Transaction model) {
        TransaccionEntity entity = new TransaccionEntity();
        entity.setId(model.getId());
        entity.setAmountTransaction(model.getAmountTransaction());
        entity.setTransactionCost(model.getTransactionCost());
        entity.setTypeTransaction(model.getTypeTransaction());
        entity.setTimeStamp(model.getTimeStamp());
        return entity;
    }
}
