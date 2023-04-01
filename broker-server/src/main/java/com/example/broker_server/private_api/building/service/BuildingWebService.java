package com.example.broker_server.private_api.building.service;

import com.example.broker_server.public_api.building.service.BuildingPublicWebService;
import org.springframework.stereotype.Service;

@Service
public class BuildingWebService {

    private final BuildingPublicWebService  buildingPublicWebService;

    public BuildingWebService(BuildingPublicWebService buildingPublicWebService) {
        this.buildingPublicWebService = buildingPublicWebService;
    }

    public void deleteBuilding() {
        // delete from database
        Long buildingId = 104L;
        buildingPublicWebService.sendDeleteBuildingEvent(buildingId);
    }

}
