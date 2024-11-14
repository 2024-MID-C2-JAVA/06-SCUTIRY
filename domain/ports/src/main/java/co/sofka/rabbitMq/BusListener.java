package co.sofka.rabbitMq;

public interface BusListener {
    void receiveMessage(String message);
}
