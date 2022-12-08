package com.example.rabbitmq.controller;

import com.example.rabbitmq.service.MessageService;
import com.example.rabbitmq.service.model.GenericResponse;
import com.example.rabbitmq.service.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createMessage(@RequestBody MessageRequest messageRequest) {
        final GenericResponse response = messageService.createMessage(messageRequest);
        return ResponseEntity.ok(response);
    }
}
