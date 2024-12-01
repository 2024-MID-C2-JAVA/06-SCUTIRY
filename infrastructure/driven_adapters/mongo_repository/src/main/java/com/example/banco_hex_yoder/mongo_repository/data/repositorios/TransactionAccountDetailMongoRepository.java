package com.example.banco_hex_yoder.mongo_repository.data.repositorios;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TransactionAccountDetailMongoRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TransactionAccountDetailMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public void saveTransactionDetail(BigDecimal montoDeposito, BigDecimal costoDeposito, String tipoDeposito, Integer cuentaOrigenNumber, Integer cuentaDestinoNumber) {
        Document detalle = new Document();
        detalle.put("montoDeposito", montoDeposito);
        detalle.put("costoDeposito", costoDeposito);
        detalle.put("tipoDeposito", tipoDeposito);
        detalle.put("cuentaOrigen", cuentaOrigenNumber);
        detalle.put("cuentaDestino", cuentaDestinoNumber);
        mongoTemplate.insert(detalle, "transaction_account_detail");
    }
}
