package com.finance.dailystockdatamicroservice;

import com.finance.dailystockdatamicroservice.config.Config;
import com.finance.dailystockdatamicroservice.services.grpc.FinanceDataServer;
//import com.finance.dailystockdatamicroservice.services.grpc.HelloWorldServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class DailyStockDataMicroServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
//        SpringApplication.run(DailyStockDataMicroServiceApplication.class, args);
////        HelloWorldServer.main(args);
//        FinanceDataServer.main(args);
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        FinanceDataServer server = context.getBean(FinanceDataServer.class);
        server.start();
        server.blockUntilShutdown();
    }

}
