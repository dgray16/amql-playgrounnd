package com.example.broker_server.public_api.handler.model;

public abstract class GenericPublicEvent {

    ServerEventType eventType;

    public void setEventType(ServerEventType eventType) {
        this.eventType = eventType;
    }

}
