package com.example.broker_server.public_api.building.controller;

import com.example.broker_server.public_api.building.model.request.BuildingPublicDto;
import com.example.broker_server.public_api.building.model.request.GetBuildingByIdPublicRequest;
import com.example.broker_server.public_api.building.model.request.UpdateBuildingPublicRequest;
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

    public boolean postAction(byte[] json) throws IOException {
        UpdateBuildingPublicRequest request = objectMapper.readValue(json, UpdateBuildingPublicRequest.class);
        System.out.println("ACTION performed on: " + request.getNewName());
        return true;
    }

}
