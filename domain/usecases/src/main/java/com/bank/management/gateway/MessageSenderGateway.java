package com.bank.management.gateway;

public interface MessageSenderGateway {
    void sendMessage(String exchange, String routingKey, String message);
}
