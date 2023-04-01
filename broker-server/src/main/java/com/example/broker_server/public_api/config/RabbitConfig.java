package com.example.broker_server.public_api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RabbitConfig {

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /** {@link Queue} that stores incoming requests to server app */
    @Bean
    Queue request() {
        return new Queue("public-request");
    }

    /* TODO create queue, binding in runtime? 1 queue per client?  */
    /* to read from 1 queue we can use Stream, but messages will stay there */
    /** {@link Queue} that stores outgoing events from server app */
    @Bean
    Queue event() {
        /*Map<String, Object> params = Map.of("x-message-ttl", Duration.ofSeconds(30L).toMillis());
        return new Queue("public-event", true, false, false, params);*/

        return new Queue("public-event");
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("public-exchange");
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(request()).to(exchange()).with("public-request");
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        String secret = HttpHeaders.encodeBasicAuth("server-prod", "secret", null);
        return builder
                .baseUrl("http://localhost:15672/api")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + secret)
                .build();
    }


}
