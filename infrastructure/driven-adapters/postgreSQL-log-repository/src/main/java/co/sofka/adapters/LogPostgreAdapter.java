package co.sofka.adapters;

import co.sofka.Log;
import co.sofka.config.PostgresLogRepository;
import co.sofka.data.LogEntity;
import co.sofka.rabbitMq.LogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<>();
        for (LogEntity logEntity : postgresLogRepository.findAll()) {
            Log log = new Log.Builder()
                    .setId(logEntity.getId())
                    .setMessage(logEntity.getMessage())
                    .setTimestamp(logEntity.getTimestamp())
                    .build();
            logs.add(log);
        }
        return logs;
    }

}
