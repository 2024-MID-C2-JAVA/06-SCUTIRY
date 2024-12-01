package com.example.banco_hex_yoder.mongo_repository.data.repositorios;

import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CustomerMongoRepository extends MongoRepository<ClienteDocument, Integer> {
    @Query("{ 'id': ?0 }")
    Optional<ClienteDocument> findById(Integer id);
}
