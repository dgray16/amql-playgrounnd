package com.example.broker_server.public_api.building.service;

import com.example.broker_server.public_api.building.model.event.BuildingDeletedEvent;
import com.example.broker_server.public_api.common.Constant;
import com.example.broker_server.public_api.common.model.ServerEventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class BuildingPublicWebService {

    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    public BuildingPublicWebService(RabbitTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    public void sendDeleteBuildingEvent(Long buildingId) {
        /* TODO add random event id? */
        BuildingDeletedEvent event = new BuildingDeletedEvent();
        event.setBuildingId(buildingId);
        event.setEventType(ServerEventType.BUILDING_DELETED);

        template.convertAndSend(Constant.CLIENT_1_EVENT_EXCHANGE, null, generateMessage(event));
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
