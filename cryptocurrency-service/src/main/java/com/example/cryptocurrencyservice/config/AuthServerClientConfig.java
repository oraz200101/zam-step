package com.example.cryptocurrencyservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AuthServerClientConfig {

    @Value("${auth.server.URI}")
    private String baseURI;

    @Bean
    public RestClient restClient() {
        return RestClient.create(baseURI);
    }
}
