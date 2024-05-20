package com.example.cryptocurrencyservice.rest_client;

import org.springframework.security.core.Authentication;

public interface AuthServerClient {

    Authentication getAuthentication(String token);

    boolean tokenIsValid(String token);
}
