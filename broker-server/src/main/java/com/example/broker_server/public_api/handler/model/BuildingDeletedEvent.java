package com.example.broker_server.public_api.handler.model;

public class BuildingDeletedEvent extends GenericPublicEvent {

    Long buildingId;

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

}
