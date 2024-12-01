// Ubicación: log_service/src/main/java/com/example/banco_hex_yoder/config/ObjectMapperConfig.java
package com.example.banco_hex_yoder.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Registra el módulo para LocalDateTime
        return mapper;
    }
}
