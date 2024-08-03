package com.dremio.apigateway.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DremioCloudConfiguration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, RateLimiter rateLimiter) {
        return builder.routes()
                 .route("dremio", r -> r
                .path("/dremio/prod/**")
                         .and()
                         .header("user-type", "NonDev")
                .filters(
                        f -> f
                        .circuitBreaker(c -> c
                                .setName("CircuitBreaker_1")
                                .setFallbackUri("forward:/fallback"))

                                .rewritePath("/dremio/prod/(?<segment>.*)", "/dremio/${segment}")
                )


                         .uri("lb://dremio-connector"))


                .route("dremio-dev", r -> r.path("/stag/dremio/**")
                        .filters(f -> f.addRequestHeader("user-type", "Dev"))
                                .uri("lb://dremio-connector-dev")
                )
                .build();
    }


}
