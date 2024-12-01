package com.example.banco_hex_yoder.gateway;


public interface Listener {
    void receiveMessage(String message);  // Método para recibir mensajes de RabbitMQ
    void saveLog(String message);         // Método para guardar logs en MongoDB
}
