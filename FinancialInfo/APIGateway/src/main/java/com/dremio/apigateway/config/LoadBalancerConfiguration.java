package com.dremio.apigateway.config;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@LoadBalancerClients(defaultConfiguration = LoadBalancerConfiguration.class)
public class LoadBalancerConfiguration {

    @LoadBalancerClient(name = "dremio-connector_num1", configuration = CustomLoadBalancerConfiguration.class)
    public class CustomLoadBalancerConfiguration {

        @Bean
        public RoundRobinLoadBalancer roundRobinLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
            String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
            return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
        }
    }

}