package com.finance.financedataservice;

import com.finance.financedataservice.config.Config;
import com.finance.financedataservice.services.GrpcFinanceDataServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


@SpringBootApplication(scanBasePackages = "com.finance.financedataservice")
public class FinanceDataServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Finance Data Service Application Started");
//        SpringApplication.run(FinanceDataServiceApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        GrpcFinanceDataServer server = context.getBean(GrpcFinanceDataServer.class);
        server.start();
        server.blockUntilShutdown();
        System.out.println("Finance Data Service Application Ended");
    }

}
