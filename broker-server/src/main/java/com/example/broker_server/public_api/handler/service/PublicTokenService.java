package com.example.broker_server.public_api.handler.service;

import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
public class PublicTokenService {

    public boolean valid(String token) throws CredentialNotFoundException {
        if (false) {
            throw new CredentialNotFoundException("Bad credentials");
        }
        return true;
    }

}
