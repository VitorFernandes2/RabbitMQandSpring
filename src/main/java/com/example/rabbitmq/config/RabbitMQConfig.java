package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    private final static String QUEUE_NAME_PROPERTY = "rabbitmq.queue.name";
    private final static String EXCHANGE_NAME_PROPERTY = "rabbitmq.exchange.name";
    private final static String ROUTING_KEY_PROPERTY = "rabbitmq.routing.key";

    private final Environment environment;

    @Autowired
    public RabbitMQConfig(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    Queue queue() {
        return new Queue(Objects.requireNonNull(environment.getProperty(QUEUE_NAME_PROPERTY)), true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(environment.getProperty(EXCHANGE_NAME_PROPERTY));
    }

    @Bean
    Binding binding(final Queue queue, final DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(environment.getProperty(ROUTING_KEY_PROPERTY));
    }

}
