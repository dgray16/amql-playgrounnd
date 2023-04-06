package com.example.broker_client.public_api.building.model.request;

import com.example.broker_client.public_api.common.model.GenericPublicRequest;

public class UpdateBuildingPublicRequest extends GenericPublicRequest {

    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
