package com.example.banco_hex_yoder.mongo_repository.data.repositorios;

import com.example.banco_hex_yoder.mongo_repository.data.entidades.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByUsername(String username);
    Optional<UserDocument> findTopByOrderByIdDesc();
}
