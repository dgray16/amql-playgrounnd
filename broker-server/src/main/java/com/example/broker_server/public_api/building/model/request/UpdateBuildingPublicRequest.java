package com.example.broker_server.public_api.building.model.request;

import com.example.broker_server.public_api.handler.model.request.GenericPublicRequest;

public class UpdateBuildingPublicRequest extends GenericPublicRequest {

    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
