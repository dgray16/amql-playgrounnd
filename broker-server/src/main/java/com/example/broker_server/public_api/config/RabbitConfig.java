package com.example.broker_server.public_api.config;

import com.example.broker_server.public_api.common.Constant;
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
    Queue toServer() {
        return new Queue(Constant.CLIENT_1_REQUEST_TO_SERVER);
    }

    /** {@link Queue} that stores outgoing events from server app */
    @Bean
    Queue responseFromServer() {
        return new Queue(Constant.CLIENT_1_RESPONSE_FROM_SERVER);
    }

    @Bean
    DirectExchange requestExchange() {
        return new DirectExchange(Constant.CLIENT_1_REQUEST_RESPONSE_EXCHANGE);
    }

    @Bean
    Binding requestBinding() {
        return BindingBuilder.bind(toServer()).to(requestExchange()).with("");
    }


    @Bean
    Queue eventFromServer() {
        return new Queue(Constant.CLIENT_1_EVENT_FROM_SERVER);
    }

    @Bean
    DirectExchange eventExchange() {
        return new DirectExchange(Constant.CLIENT_1_EVENT_EXCHANGE);
    }

    @Bean
    Binding eventBinding() {
        return BindingBuilder.bind(eventFromServer()).to(eventExchange()).with("");
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
