package com.example.broker_client.public_api.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class DefaultAmqpSupport {


    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    protected DefaultAmqpSupport(RabbitTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    protected <T> T fireAndReceive(Object dto, ParameterizedTypeReference<T> responseType) {
        Supplier<T> supplier = () -> Optional
                .ofNullable(template.convertSendAndReceiveAsType(
                        "client-1-request-response-exchange", null, generateMessage(dto), responseType
                ))
                .orElseThrow();

        return catchServerException(supplier);
    }

    private <T> T catchServerException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException("Server application error", e);
        }
    }

    private Message generateMessage(Object dto) {
        try {
            return MessageBuilder
                    .withBody(objectMapper.writeValueAsBytes(dto))
                    .andProperties(MessagePropertiesBuilder.newInstance().setContentType(MediaType.APPLICATION_JSON_VALUE).build())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Message cannot be constructed");
        }
    }

}