package com.example.banco_hex_yoder.postgresql_repository.data.repositorios;

import com.example.banco_hex_yoder.postgresql_repository.data.entidades.CuentaEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.useDB", havingValue = "0")
public interface AccountRepository extends JpaRepository<CuentaEntity, Integer> {
    List<CuentaEntity> findByCustomerId(Integer customerId);
    Optional<CuentaEntity> findByNumber(Integer number);
}
