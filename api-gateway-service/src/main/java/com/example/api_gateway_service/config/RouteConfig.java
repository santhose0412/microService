package com.example.api_gateway_service.config;

import com.example.api_gateway_service.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("product-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://PRODUCT-SERVICE"))
                .route("order-service", r -> r.path("/order/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://ORDER-SERVICE"))
                .route("auth-service", r -> r.path("/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .build();
    }
}
