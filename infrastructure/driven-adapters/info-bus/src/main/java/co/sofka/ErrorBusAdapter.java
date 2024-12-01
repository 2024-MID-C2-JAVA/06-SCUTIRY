package co.sofka;

import co.sofka.rabbitMq.ErrorBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorBusAdapter implements ErrorBus {

    private final RabbitTemplate rabbitTemplate;

    @Value("${EXCHANGE.NAME}")
    private String EXCHANGE_NAME;

    @Value("${ERROR_ROUTING_KEY}")
    private String ERROR_ROUTING_KEY;

    public ErrorBusAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendErrorMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ERROR_ROUTING_KEY, message);
    }
}
