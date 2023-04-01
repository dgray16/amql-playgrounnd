package com.example.broker_server.public_api.handler.controller;

import com.example.broker_server.public_api.building.controller.BuildingPublicController;
import com.example.broker_server.public_api.handler.model.GenericPublicRequest;
import com.example.broker_server.public_api.handler.service.PublicTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Objects;

@Component
public class ControllerHandler {

    private final BuildingPublicController buildingController;

    private final PublicTokenService tokenService;
    private final ObjectMapper objectMapper;

    public ControllerHandler(BuildingPublicController buildingController, PublicTokenService tokenService, ObjectMapper objectMapper) {
        this.buildingController = buildingController;
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "public-request")
    public Object handle(Message message) {
        Object result = null;

        /* If we receive incorrect message from queue, we acknowledge and suppress it */
        if (StringUtils.hasText(message.getMessageProperties().getReplyTo())) {
            try {

                GenericPublicRequest rpcDto = objectMapper.readValue(message.getBody(), GenericPublicRequest.class);

                if (tokenService.valid(rpcDto.getToken())) {
                    Object response = switch (rpcDto.getActionType()) {
                        case GET_BUILDING -> buildingController.getBuilding(message.getBody());
                        case POST_ACTION -> buildingController.postAction();
                    };

                    if (!(Objects.isNull(response))) {
                        result = response;
                    }
                }
            } catch (CredentialNotFoundException e) {
                result = MessageBuilder.withBody("Bad credentials".getBytes()).build();
            } catch (Exception e) {
                result = MessageBuilder.withBody("Server error".getBytes()).build();
            }
        }


        return result;
    }
}
