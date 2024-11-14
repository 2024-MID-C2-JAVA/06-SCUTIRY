package co.sofka.adapters;

import co.sofka.Log;
import co.sofka.config.PostgresLogRepository;
import co.sofka.data.LogEntity;
import co.sofka.rabbitMq.LogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LogPostgreAdapter implements LogRepository {

    private final PostgresLogRepository postgresLogRepository;

    public LogPostgreAdapter(PostgresLogRepository postgresLogRepository) {
        this.postgresLogRepository = postgresLogRepository;
    }

    @Override
    public void apply(Log log) {
        LogEntity logEntity = new LogEntity();
        logEntity.setMessage(log.getMessage());
        postgresLogRepository.save(logEntity);
    }
}
