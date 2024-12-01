package co.sofka.logservice;

import co.sofka.Log;
import co.sofka.rabbitMq.LogRepository;

import java.util.List;

public class GetLogsUseCase {

    private final LogRepository logRepository;

    public GetLogsUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log>apply(){
        return logRepository.getLogs();
    }
}
