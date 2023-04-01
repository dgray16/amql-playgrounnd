package com.example.broker_server.public_api.building.service;

import com.example.broker_server.public_api.handler.model.BuildingDeletedEvent;
import com.example.broker_server.public_api.handler.model.ServerEventType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BuildingPublicWebService {

    private final RabbitTemplate template;

    public BuildingPublicWebService(RabbitTemplate template) {
        this.template = template;
    }

    public void sendDeleteBuildingEvent(Long buildingId) {
        /* TODO add random event id? */
        BuildingDeletedEvent event = new BuildingDeletedEvent();
        event.setBuildingId(buildingId);
        event.setEventType(ServerEventType.BUILDING_DELETED);

        template.convertAndSend("event", event);
    }

}
