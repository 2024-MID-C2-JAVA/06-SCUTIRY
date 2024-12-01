package com.example.banco_hex_yoder.mongo_repository.data.mappers;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.AccountDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DocumentMapper {

    public static Account toModel(AccountDocument document) {
        Account account = new Account();

        account.setId(document.getId() != null ? Integer.valueOf(document.getId().toHexString()) : null);
        account.setNumber(document.getNumber());
        account.setAmount(document.getAmount());
        account.setCustomerId(document.getCustomerId());

        account.setCreatedAt(document.getCreatedAt() != null ?
                document.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate() : null);
        account.setDeleted(document.isDeleted());
        return account;
    }

    public static AccountDocument toDocument(Account model) {
        AccountDocument document = new AccountDocument();

        document.setId(model.getId() != null ? new ObjectId(String.valueOf(model.getId())) : null);
        document.setNumber(model.getNumber());
        document.setAmount(model.getAmount());
        document.setCustomerId(model.getCustomerId());

        document.setCreatedAt(model.getCreatedAt() != null ?
                model.getCreatedAt().atStartOfDay(ZoneId.systemDefault()).toInstant() : null);
        document.setDeleted(model.isDeleted());
        return document;
    }
}
