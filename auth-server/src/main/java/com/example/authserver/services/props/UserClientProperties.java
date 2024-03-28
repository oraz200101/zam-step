package com.example.authserver.services.props;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Collections;

@Configuration
public class UserClientProperties {

    @Value("${user.client.url}")
    private String url;

    @Bean
    public RestClient userClient() {
        return RestClient.builder()
                        .baseUrl(url)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .defaultUriVariables(Collections.singletonMap("url", url))
                        .build();
    }
}
