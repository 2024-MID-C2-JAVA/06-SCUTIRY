package co.sofka.rabbitMq;

import co.sofka.Log;

import java.util.List;

public interface LogRepository {
    void apply(Log log);
    List<Log>getLogs();
}
