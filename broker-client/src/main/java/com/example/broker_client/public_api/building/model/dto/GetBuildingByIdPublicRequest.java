package com.example.broker_client.public_api.building.model.dto;

import com.example.broker_client.public_api.common.model.GenericPublicDto;

public class GetBuildingByIdPublicRequest extends GenericPublicDto {

    Long buildingId;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

}