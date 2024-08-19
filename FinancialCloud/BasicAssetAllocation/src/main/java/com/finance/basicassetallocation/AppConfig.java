package com.finance.basicassetallocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")

public class AppConfig {

    @Value("${app.host}")
    private static String appHost;

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


    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public void printProperties() {
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
    }
}