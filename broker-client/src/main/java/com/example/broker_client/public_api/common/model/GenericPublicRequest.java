package com.example.broker_client.public_api.common.model;

public class GenericPublicRequest {

    String token;
    ActionType actionType;

    public String getToken() {
        return token;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}