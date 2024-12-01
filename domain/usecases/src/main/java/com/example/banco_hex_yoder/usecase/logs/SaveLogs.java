package com.example.banco_hex_yoder.usecase.logs;

import com.example.banco_hex_yoder.gateway.Listener;

public class SaveLogs {

    private final Listener listener;

    public SaveLogs(Listener listener) {
        this.listener = listener;
    }

    public void saveLog(String message) {
        listener.saveLog(message);
    }
}