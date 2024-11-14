package co.sofka.handler;

import co.sofka.Log;
import co.sofka.data.LogDto;
import co.sofka.logservice.SaveLogUseCase;
import org.springframework.stereotype.Component;

@Component
public class LogHandler {

    private final SaveLogUseCase saveLogUseCase;

    public LogHandler(SaveLogUseCase saveLogUseCase) {
        this.saveLogUseCase = saveLogUseCase;
    }

    public void saveLog(LogDto logDto) {
        saveLogUseCase.apply(new Log.Builder()
                .setMessage(logDto.getMessage())
                .build());
    }
}
