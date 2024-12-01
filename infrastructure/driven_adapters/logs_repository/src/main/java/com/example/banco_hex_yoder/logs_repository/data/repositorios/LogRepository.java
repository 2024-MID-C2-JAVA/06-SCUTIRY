package com.example.banco_hex_yoder.logs_repository.data.repositorios;

import com.example.banco_hex_yoder.logs_repository.data.entidades.LogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

    public interface LogRepository extends MongoRepository<LogDocument, String> {

    }
