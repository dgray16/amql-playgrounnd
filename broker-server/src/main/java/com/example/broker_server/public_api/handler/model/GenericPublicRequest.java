package com.example.broker_server.public_api.handler.model;

public class GenericPublicRequest {

    private String token;
    private RequestActionType actionType;

    public String getToken() {
        return token;
    }

    public RequestActionType getActionType() {
        return actionType;
    }
}
