package com.example.broker_server.public_api.building.model.event;

import com.example.broker_server.public_api.common.model.GenericPublicEvent;

public class BuildingDeletedEvent extends GenericPublicEvent {

    Long buildingId;

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }


    public Long getBuildingId() {
        return buildingId;
    }
}
