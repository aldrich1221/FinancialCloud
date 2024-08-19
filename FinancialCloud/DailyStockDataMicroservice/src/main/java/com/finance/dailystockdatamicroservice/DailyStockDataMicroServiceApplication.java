package com.finance.dailystockdatamicroservice;

import com.finance.dailystockdatamicroservice.services.grpc.HelloWorldServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DailyStockDataMicroServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
//        SpringApplication.run(DailyStockDataMicroServiceApplication.class, args);
        HelloWorldServer.main(args);
    }

}
