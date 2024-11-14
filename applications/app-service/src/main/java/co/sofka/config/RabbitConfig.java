package co.sofka.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${EXCHANGE.NAME}")
    private String exchangeName;

    @Value("${SUCCESS_ROUTING_KEY}")
    private String SUCCESS_KEY;

    @Value("${ERROR_ROUTING_KEY}")
    private String ERROR_KEY;

    @Value("${SUCCESS_QUEUE_NAME}")
    private String successQueueName;

    @Value("${ERROR_QUEUE_NAME}")
    private String errorQueueName;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue successQueue() {
        return new Queue(successQueueName, true);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(errorQueueName, true);
    }


    @Bean
    public Binding successBinding(Queue successQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(successQueue)
                .to(exchange)
                .with(SUCCESS_KEY);
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(errorQueue)
                .to(exchange)
                .with(ERROR_KEY);
    }
}
