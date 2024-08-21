package com.finance.financedataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceDataServiceApplication {

    public static void main(String[] args) {
        System.out.println("Finance Data Service Application Started");
        SpringApplication.run(FinanceDataServiceApplication.class, args);
        System.out.println("Finance Data Service Application Ended");
    }

}
