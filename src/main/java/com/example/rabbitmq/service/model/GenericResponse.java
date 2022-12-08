package com.example.rabbitmq.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

    private EStatus status;

}
