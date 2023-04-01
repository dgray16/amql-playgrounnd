package com.example.broker_server.public_api.init.model;

public record CreateRabbitPermissionRequest(String configure, String write, String read) {
}
