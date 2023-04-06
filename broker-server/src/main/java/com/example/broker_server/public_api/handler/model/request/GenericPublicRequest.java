package com.example.broker_server.public_api.handler.model.request;

public class GenericPublicRequest {

    private String token;
    private RequestActionType actionType;

    public String getToken() {
        return token;
    }

    public RequestActionType getActionType() {
        return actionType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActionType(RequestActionType actionType) {
        this.actionType = actionType;
    }
}
