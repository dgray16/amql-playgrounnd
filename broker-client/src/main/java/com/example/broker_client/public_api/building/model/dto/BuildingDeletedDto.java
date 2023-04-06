package com.example.broker_client.public_api.building.model.dto;

import com.example.broker_client.public_api.common.model.PublicEventDto;
import com.example.broker_client.public_api.common.model.ServerEventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildingDeletedDto extends PublicEventDto {

    private Long buildingId;

    @JsonCreator
    public BuildingDeletedDto(@JsonProperty(required = true) Long buildingId,
                              @JsonProperty(required = true) ServerEventType serverEventType) {
        this.buildingId = buildingId;
        setServerEventType(serverEventType);
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
