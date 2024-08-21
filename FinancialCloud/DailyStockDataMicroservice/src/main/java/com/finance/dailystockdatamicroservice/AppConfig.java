package com.finance.dailystockdatamicroservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppConfig {

    @Value("${app.host}")
    private static String appHost;

    @Value("${grpc.service.stockdata.uri}")
    private static String grpcServiceStockDataUri;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public static String getGrpcServiceStockDataUri() {
        return grpcServiceStockDataUri;
    }

    public void setGrpcServiceStockDataUri(String grpcServiceStockDataUri) {
        this.grpcServiceStockDataUri = grpcServiceStockDataUri;
    }

    public static String getAppHost() {
        return appHost;
    }

    private static List<String> validstocks;
    public  static List<String> getValidstocks() {
        return validstocks;
    }
    public static void setValidstocks(List<String> validstocks) {
       validstocks = validstocks;
    }




    public void printProperties() {
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
    }
}