package com.example.broker_client.public_api.common.model;

public class PublicEventDto {

    private ServerEventType serverEventType;

    public ServerEventType getServerEventType() {
        return serverEventType;
    }

    public void setServerEventType(ServerEventType serverEventType) {
        this.serverEventType = serverEventType;
    }
}
