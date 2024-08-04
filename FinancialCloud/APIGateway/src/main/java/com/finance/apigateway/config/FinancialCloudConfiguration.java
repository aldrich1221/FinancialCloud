package com.dremio.apigateway.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class FinancialCloudConfiguration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, RateLimiter rateLimiter) {
        return builder.routes()
                 .route("indicator", r -> r
                .path("/indicator/**")
                         .and()
                         .header("user-type", "NonDev")
                .filters(
                        f -> f
                        .circuitBreaker(c -> c
                                .setName("CircuitBreaker_1")
                                .setFallbackUri("forward:/fallback"))
                                .rewritePath("/indicator/(?<segment>.*)", "/indicator/${segment}")
                )

                         .uri("lb://RiskFactorMicroService"))

                .build();
    }


}