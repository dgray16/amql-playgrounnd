package com.example.broker_server.public_api.init;

import com.example.broker_server.public_api.common.Constant;
import com.example.broker_server.public_api.init.model.CreateRabbitPermissionRequest;
import com.example.broker_server.public_api.init.model.CreateRabbitUserRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/** <a href="https://rawcdn.githack.com/rabbitmq/rabbitmq-server/v3.11.13/deps/rabbitmq_management/priv/www/api/index.html">RabbitMQ HTTP API</a> */
@Component
public class RabbitInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final WebClient webClient;

    public RabbitInitializer(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createClient1User();
        createClient1UserPermissions();

        createClient2User();
    }

    private void createClient1User() {
        CreateRabbitUserRequest createUserRequest = new CreateRabbitUserRequest("secret", Constant.CLIENT_1);

        webClient
                .put()
                .uri("/users/" + Constant.CLIENT_1)
                .bodyValue(createUserRequest)
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10L));
    }

    private void createClient2User() {
        CreateRabbitUserRequest createUserRequest = new CreateRabbitUserRequest("secret", "client-2");

        webClient
                .put()
                .uri("/users/client-2")
                .bodyValue(createUserRequest)
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10L));
    }

    private void createClient1UserPermissions() {
        CreateRabbitPermissionRequest request = new CreateRabbitPermissionRequest("", "public-exchange", "^client-1-.*");

        webClient
                .put()
                .uri(x -> x.path("/permissions").pathSegment("/").path("/" + Constant.CLIENT_1).build())
                .bodyValue(request)
                .retrieve()
                .toEntity(String.class)
                .block(Duration.ofSeconds(10L));
    }

}
