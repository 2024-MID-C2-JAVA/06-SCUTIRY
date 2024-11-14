package co.sofka.rabbitMq;

import co.sofka.Log;

public interface LogRepository {
    void apply(Log log);
}
