package kz.userservice.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AuthServerClientConfig {

    @Value("${auth.server.URI}")
    String baseURI;

    @Bean
    RestClient restClient() {
        return RestClient.create(baseURI);
    }
}