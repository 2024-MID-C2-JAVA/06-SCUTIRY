package co.sofka.rabbitMq;

public interface SuccessBus {
    void sendSuccessMessage(String message);
}
