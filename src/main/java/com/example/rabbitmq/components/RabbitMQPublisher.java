package com.example.rabbitmq.components;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQPublisher {

    private final static String EXCHANGE_NAME_PROPERTY = "rabbitmq.exchange.name";
    private final static String ROUTING_KEY_PROPERTY = "rabbitmq.routing.key";

    private final AmqpTemplate rabbitTemplate;
    private final Environment environment;

    @Autowired
    public RabbitMQPublisher(final AmqpTemplate rabbitTemplate, final Environment environment) {
        this.rabbitTemplate = rabbitTemplate;
        this.environment = environment;
    }

    public void sendMessage(final String messageBody) {
        final MessageProperties messageProperties = MessagePropertiesBuilder.newInstance()
                .setHeader("header-test", "value-test")
                .build();
        final Message message = MessageBuilder
                .withBody(messageBody.getBytes(StandardCharsets.UTF_8))
                .andProperties(messageProperties)
                .build();

        final String exchangeName = environment.getProperty(EXCHANGE_NAME_PROPERTY);
        final String routingKey = environment.getProperty(ROUTING_KEY_PROPERTY);

        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
