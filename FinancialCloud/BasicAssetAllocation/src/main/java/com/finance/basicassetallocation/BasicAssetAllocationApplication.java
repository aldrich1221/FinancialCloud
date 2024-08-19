package com.finance.basicassetallocation;

import com.finance.basicassetallocation.grpc.HelloWorldClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class BasicAssetAllocationApplication {

	public static void main(String[] args) throws Exception {


		final AppConfig appConfig = new AppConfig();

		appConfig.printProperties();

//		HelloWorldClient.main(args);
		SpringApplication.run(BasicAssetAllocationApplication.class, args);
	}
	public CommandLineRunner run(AppConfig myComponent) {
		return args -> myComponent.printProperties();
	}

}
