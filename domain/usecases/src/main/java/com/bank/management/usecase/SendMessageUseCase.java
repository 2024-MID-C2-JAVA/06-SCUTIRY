package com.bank.management.usecase;

import com.bank.management.gateway.MessageSenderGateway;

public class SendMessageUseCase {

    private final MessageSenderGateway messageSender;

    public SendMessageUseCase(MessageSenderGateway messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String recipient, String message) {
        messageSender.sendMessage(recipient, message);
    }
}
