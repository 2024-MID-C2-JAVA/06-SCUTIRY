package co.sofka.rabbitMq;

public interface Bus {
    void sendMessage(String message,boolean isSuccess);
}
