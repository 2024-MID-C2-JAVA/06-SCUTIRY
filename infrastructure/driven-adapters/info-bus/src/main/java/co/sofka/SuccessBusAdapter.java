package co.sofka;

import co.sofka.rabbitMq.SuccessBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SuccessBusAdapter implements SuccessBus {

    private final RabbitTemplate rabbitTemplate;

    @Value("${EXCHANGE.NAME}")
    private String EXCHANGE_NAME;

    @Value("${SUCCESS_ROUTING_KEY}")
    private String SUCCESS_ROUTING_KEY;

    public SuccessBusAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendSuccessMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, SUCCESS_ROUTING_KEY, message);
    }

}
