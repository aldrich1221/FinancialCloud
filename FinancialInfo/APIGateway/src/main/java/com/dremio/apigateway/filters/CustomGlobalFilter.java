//package com.dremio.apigateway.filters;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class CustomGlobalFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // Add custom logic before the request is forwarded
//        exchange.getRequest().mutate().header("X-Custom-Header", "CustomValue").build();
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            // Add custom logic after the response is received
//            exchange.getResponse().getHeaders().add("X-Custom-Response-Header", "CustomValue");
//        }));
//    }
//
//    @Override
//    public int getOrder() {
//        // Order of the filter
//        return Ordered.LOWEST_PRECEDENCE;
//    }
//}
