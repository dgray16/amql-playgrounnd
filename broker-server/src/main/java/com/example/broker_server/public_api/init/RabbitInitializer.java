package com.example.broker_server.public_api.init;

import com.example.broker_server.public_api.init.model.CreateRabbitPermissionRequest;
import com.example.broker_server.public_api.init.model.CreateRabbitUserRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class RabbitInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final WebClient webClient;

    public RabbitInitializer(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // TODO create user if not exists
        // TODO create user limits
        createClientUser();
        createClientUserPermissions();
    }

    private void createClientUser() {
        CreateRabbitUserRequest createUserRequest = new CreateRabbitUserRequest("secret", "public-client");

        webClient
                .put()
                .uri("/users/client-prod")
                .bodyValue(createUserRequest)
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10L));

        // TODO handle error
    }

    private void createClientUserPermissions() {
        CreateRabbitPermissionRequest request = new CreateRabbitPermissionRequest("", "public-exchange", "public-.*");

        webClient
                .put()
                .uri(x -> x.path("/permissions").pathSegment("/").path("/client-prod").build())
                .bodyValue(request)
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10L));

        // TODO handle error
    }

}
