package com.example.banco_hex_yoder.postgresql_repository.data.repositorios;

import com.example.banco_hex_yoder.postgresql_repository.data.entidades.TransaccionEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "app.useDB", havingValue = "0")
public interface TransactionRepository extends JpaRepository<TransaccionEntity, Integer> {

}
