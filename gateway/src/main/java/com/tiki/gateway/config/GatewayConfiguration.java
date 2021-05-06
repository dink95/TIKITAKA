package com.tiki.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/mbr/**")
                        .uri("http://localhost:8081/"))

                .route(r -> r.path("/comp/**")
                        .uri("http://localhost:8081/"))

                .route(r -> r.path("/prd/**")
                        .uri("http://localhost:8082/"))

                .route(r -> r.path("/cat/**")
                        .uri("http://localhost:8082/"))

                .build();

    }



}
