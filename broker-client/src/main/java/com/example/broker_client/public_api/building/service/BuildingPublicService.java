package com.example.broker_client.public_api.building.service;

import com.example.broker_client.private_api.building.model.BuildingDto;
import com.example.broker_client.public_api.building.model.dto.GetBuildingByIdPublicRequest;
import com.example.broker_client.public_api.common.model.ActionType;
import com.example.broker_client.public_api.building.model.dto.BuildingPublicDto;
import com.example.broker_client.public_api.common.service.DefaultAmqpSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingPublicService extends DefaultAmqpSupport {

    public BuildingPublicService(RabbitTemplate template, ObjectMapper objectMapper) {
        super(template, objectMapper);
    }

    @GetMapping(value = "public-api/v1/test")
    public BuildingDto getBuilding() {
        GetBuildingByIdPublicRequest request = new GetBuildingByIdPublicRequest();
        request.setToken("abc123");
        request.setActionType(ActionType.GET_BUILDING);
        request.setBuildingId(104L);

        BuildingPublicDto response = fireAndReceive(request, ParameterizedTypeReference.forType(BuildingPublicDto.class));

        return new BuildingDto(response.name());
    }

    @RabbitListener(queues = "public-event")
    void handle(Message message) {
        System.out.println("");
    }

}