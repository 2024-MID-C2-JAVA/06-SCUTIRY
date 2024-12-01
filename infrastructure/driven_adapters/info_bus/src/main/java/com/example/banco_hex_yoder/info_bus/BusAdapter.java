package com.example.banco_hex_yoder.info_bus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import com.example.banco_hex_yoder.gateway.Bus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class BusAdapter implements Bus {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;
    private final ObjectMapper objectMapper;

    public BusAdapter(RabbitTemplate rabbitTemplate,
                      @Value("${app.rabbitmq.exchange}") String exchangeName,
                      @Value("${app.rabbitmq.routingkey}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }


    @Override
    public void sendTransactionLog(String tipoOperacion, Integer cuentaOrigen, Integer cuentaDestino, BigDecimal monto, BigDecimal costoOperacion, BigDecimal saldoFinal, String detalles) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String rolUsuario = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");


        Map<String, Object> logData = new HashMap<>();
        logData.put("tipoOperacion", tipoOperacion);
        logData.put("cuentaOrigen", cuentaOrigen);
        logData.put("cuentaDestino", cuentaDestino);
        logData.put("monto", monto);
        logData.put("costoOperacion", costoOperacion);
        logData.put("saldoFinal", saldoFinal);

        logData.put("fechaTransaccion", LocalDateTime.now().toString());
        logData.put("rolUsuario", rolUsuario);
        logData.put("detalles", detalles);

        try {

            String jsonMessage = objectMapper.writeValueAsString(logData);


            MessageProperties properties = new MessageProperties();
            properties.setContentType("application/json");


            Message message = new Message(jsonMessage.getBytes(), properties);
            rabbitTemplate.send(exchangeName, routingKey, message);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el mensaje a RabbitMQ", e);
        }
    }
}
