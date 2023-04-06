package com.example.broker_server.private_api.building.service;

import com.example.broker_server.public_api.building.service.BuildingPublicWebService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class BuildingWebService {

    private final BuildingPublicWebService  buildingPublicWebService;

    public BuildingWebService(BuildingPublicWebService buildingPublicWebService) {
        this.buildingPublicWebService = buildingPublicWebService;
    }

    @PostMapping(value = "public-api/test-delete")
    public void deleteBuilding() {
        // delete from database
        Long buildingId = 104L;
        buildingPublicWebService.sendDeleteBuildingEvent(buildingId);
    }

}
