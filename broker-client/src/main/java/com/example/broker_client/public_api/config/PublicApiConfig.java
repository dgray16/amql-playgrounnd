package com.example.broker_client.public_api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublicApiConfig {

    @Bean
    Queue request() {
        return new Queue("public-request");
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
    Queue event() {
        // Map<String, Object> params = Map.of("x-message-ttl", Duration.ofSeconds(30L).toMillis());
        // return new Queue("public-event", true, false, false, params);
        return new Queue("public-event");
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}