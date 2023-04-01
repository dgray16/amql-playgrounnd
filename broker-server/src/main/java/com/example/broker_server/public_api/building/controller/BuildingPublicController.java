package com.example.broker_server.public_api.building.controller;

import com.example.broker_server.public_api.building.model.dto.BuildingPublicDto;
import com.example.broker_server.public_api.building.model.request.GetBuildingByIdPublicRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BuildingPublicController {

    private final ObjectMapper objectMapper;

    public BuildingPublicController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BuildingPublicDto getBuilding(byte[] json) throws IOException {
        GetBuildingByIdPublicRequest request = objectMapper.readValue(json, GetBuildingByIdPublicRequest.class);
        return new BuildingPublicDto("Building id = " + request.getBuildingId());
    }

    public boolean postAction() {
        System.out.println("ACTION performed");
        return true;
    }

}
