package com.example.broker_server.public_api.common.model;

public abstract class GenericPublicEvent {

    ServerEventType eventType;

    public void setEventType(ServerEventType eventType) {
        this.eventType = eventType;
    }

    public ServerEventType getEventType() {
        return eventType;
    }
}
