package com.example.banco_hex_yoder.mongo_repository.data.repositorios;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TransactionMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TransactionMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String saveTransaction(BigDecimal monto, BigDecimal costo, String tipoTransaccion) {
        Document transaccion = new Document();
        transaccion.put("amountTransaction", monto);
        transaccion.put("transactionCost", costo);
        transaccion.put("typeTransaction", tipoTransaccion);
        transaccion.put("timeStamp", System.currentTimeMillis());

        mongoTemplate.insert(transaccion, "transaction");

        return transaccion.getObjectId("_id").toString();
    }
}
