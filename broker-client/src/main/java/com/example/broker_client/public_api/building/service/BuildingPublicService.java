package com.example.broker_client.public_api.building.service;

import com.example.broker_client.private_api.building.model.BuildingDto;
import com.example.broker_client.public_api.building.model.dto.BuildingDeletedDto;
import com.example.broker_client.public_api.building.model.dto.GetBuildingByIdPublicRequest;
import com.example.broker_client.public_api.building.model.request.UpdateBuildingPublicRequest;
import com.example.broker_client.public_api.common.model.ActionType;
import com.example.broker_client.public_api.building.model.dto.BuildingPublicDto;
import com.example.broker_client.public_api.common.service.DefaultAmqpSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
public class BuildingPublicService extends DefaultAmqpSupport {

    private ObjectMapper objectMapper;

    public BuildingPublicService(RabbitTemplate template, ObjectMapper objectMapper) {
        super(template, objectMapper);
        this.objectMapper = objectMapper;
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

    @PostMapping(value = "pubic-api/test-update")
    void updateBuilding(@RequestParam String newName) {
        UpdateBuildingPublicRequest request = new UpdateBuildingPublicRequest();
        request.setNewName(newName);
        request.setToken("abc123");
        request.setActionType(ActionType.UPDATE_BUILDING);

        Boolean response = fireAndReceive(request, ParameterizedTypeReference.forType(Boolean.TYPE));

        if (Boolean.FALSE.equals(response)) {
            throw new RuntimeException("Update failed");
        }
    }

    @RabbitListener(queues = "client-1-event-from-server")
    void handle(Message message) throws IOException {
        BuildingDeletedDto dto = objectMapper.readValue(message.getBody(), BuildingDeletedDto.class);

        if (Objects.nonNull(dto)) {
            System.out.println("New event from server");
        } else {
            throw new RuntimeException("Error");
        }
    }

}