package co.sofka.config;

import co.sofka.data.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresLogRepository extends JpaRepository<LogEntity,Integer> {
}
