package com.example.banco_hex_yoder.config;


import com.example.banco_hex_yoder.gateway.Listener;
import com.example.banco_hex_yoder.usecase.logs.SaveLogs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

        private final Listener listener;

        public UseCaseConfig(Listener listener) {
                this.listener = listener;
        }

        @Bean
        public SaveLogs saveLogs() {
                return new SaveLogs(listener);
        }
}
