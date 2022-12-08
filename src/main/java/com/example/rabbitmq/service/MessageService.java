package com.example.rabbitmq.service;

import com.example.rabbitmq.components.RabbitMQPublisher;
import com.example.rabbitmq.service.model.EStatus;
import com.example.rabbitmq.service.model.GenericResponse;
import com.example.rabbitmq.service.model.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    private final RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    public MessageService(final RabbitMQPublisher rabbitMQPublisher) {
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    public GenericResponse createMessage(final MessageRequest messageRequest) {
        final String message = messageRequest.getMessage();
        rabbitMQPublisher.sendMessage(message);
        final GenericResponse response = GenericResponse.builder()
                .status(EStatus.SUCCESS)
                .build();
        return response;
    }
}
