package co.sofka.handler;

import co.sofka.Log;
import co.sofka.data.LogDtoListener;
import co.sofka.logservice.SaveLogUseCase;
import org.springframework.stereotype.Component;

@Component
public class LogHandler {

    private final SaveLogUseCase saveLogUseCase;

    public LogHandler(SaveLogUseCase saveLogUseCase) {
        this.saveLogUseCase = saveLogUseCase;
    }

    public void saveLog(LogDtoListener logDtoListener) {
        saveLogUseCase.apply(new Log.Builder()
                .setMessage(logDtoListener.getMessage())
                .build());
    }
}
