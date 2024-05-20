package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Value("${api.routes.auth}")
    private String authURI;

    @Value("${api.routes.user-service}")
    private String userServiceURI;

    @Value("${api.routes.health-service}")
    private String healthServiceURI;

    @Value("${api.routes.cryptocurrency-transaction-service}")
    private String cryptocurrencyTransactionServiceURI;

    @Value("${api.routes.cryptocurrency-wallet-service}")
    private String cryptocurrencyWalletServiceURI;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                                  .route("auth-route",
                                         r -> r.path("/api/auth/**").uri(authURI))
                                  .route("user-service-route",
                                         r -> r.path("/api/user/**").uri(userServiceURI))
                                  .route("health-service-route",
                                         r -> r.path("/api/health-analysis/**").uri(healthServiceURI))
                                  .route("cryptocurrency-service-wallet-route",
                                         r -> r.path("/api/wallets/**").uri(cryptocurrencyWalletServiceURI))
                                  .route("cryptocurrency-service-transaction-route",
                                         r -> r.path("/api/transactions/**").uri(cryptocurrencyTransactionServiceURI))
                                  .build();
    }
}
