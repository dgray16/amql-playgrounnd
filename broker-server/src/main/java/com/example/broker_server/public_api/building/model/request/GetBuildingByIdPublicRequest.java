package com.example.broker_server.public_api.building.model.request;

import com.example.broker_server.public_api.handler.model.request.GenericPublicRequest;

public class GetBuildingByIdPublicRequest extends GenericPublicRequest {

    private Long buildingId;

    public Long getBuildingId() {
        return buildingId;
    }
}
