package com.example.broker_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class BrokerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokerClientApplication.class, args);
    }

}
