package com.example.banco_hex_yoder;

import com.example.banco_hex_yoder.gateway.Listener;
import com.example.banco_hex_yoder.logs_repository.data.entidades.LogDocument;
import com.example.banco_hex_yoder.logs_repository.data.repositorios.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MqListener implements Listener {

    private final LogRepository logRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public MqListener(LogRepository logRepository, ObjectMapper objectMapper) {
        this.logRepository = logRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        saveLog(message);
    }

    @Override
    public void saveLog(String message) {
        try {
            LogDocument log = objectMapper.readValue(message, LogDocument.class);
            logRepository.save(log);
            System.out.println("Log saved to MongoDB: " + log);
        } catch (Exception e) {
            System.err.println("Error saving log: " + e.getMessage());
        }
    }
}