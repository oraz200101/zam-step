package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
    // todo Oraz route нужно перенести в конфиг
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                                  .route("auth-route", r -> r.path("/api/auth/**").uri("http://localhost:8081/api/auth"))
                                  .route("user-service-rout", r->r.path("/api/user/**").uri("http://localhost:8082/api/user"))
                                  .build();
    }
}
