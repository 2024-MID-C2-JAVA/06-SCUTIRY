package co.sofka.logservice;

import co.sofka.Log;
import co.sofka.rabbitMq.LogRepository;

public class SaveLogUseCase {

    private final LogRepository logRepository;

    public SaveLogUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void apply(Log log) {
        logRepository.apply(log);
    }
}
