package co.sofka.handler;

import co.sofka.Log;
import co.sofka.data.LogDto;
import co.sofka.logservice.GetLogsUseCase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Handler {

    private final GetLogsUseCase getLogsUseCase;

    public Handler(GetLogsUseCase getLogsUseCase) {
        this.getLogsUseCase = getLogsUseCase;
    }

    public List<LogDto> getLogs() {
        List<LogDto> logDtos = new ArrayList<>();
        List<Log> logs = getLogsUseCase.apply();

        for (Log log : logs) {
            LogDto logDto = new LogDto.Builder()
                    .setId(log.getId())
                    .setMessage(log.getMessage())
                    .setTimestamp(log.getTimestamp())
                    .build();
            logDtos.add(logDto);
        }

        return logDtos;
    }
}
