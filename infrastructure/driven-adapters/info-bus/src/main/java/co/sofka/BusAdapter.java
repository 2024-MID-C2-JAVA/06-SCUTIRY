package co.sofka;

import co.sofka.rabbitMq.Bus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BusAdapter implements Bus {

    private final RabbitTemplate rabbitTemplate;

    @Value("${EXCHANGE.NAME}")
    private String EXCHANGE_NAME;

    @Value("${SUCCESS_ROUTING_KEY}")
    private String SUCCESS_ROUTING_KEY;

    @Value("${ERROR_ROUTING_KEY}")
    private String ERROR_ROUTING_KEY;

    public BusAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message, boolean isSuccess) {
        String routingKey = isSuccess ? SUCCESS_ROUTING_KEY : ERROR_ROUTING_KEY;
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
    }
}
