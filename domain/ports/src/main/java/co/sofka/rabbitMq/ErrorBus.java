package co.sofka.rabbitMq;

public interface ErrorBus {
    void sendErrorMessage(String message);
}
