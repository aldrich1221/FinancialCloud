package com.finance.financedataservice.config;
import com.finance.financedataservice.repositories.FinanceDocumentRepository;
import com.finance.financedataservice.services.GrpcFinanceDataServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author mhmdz
 * Created By Zeeshan on 20-05-2023
 * @project oauth-jwt
 */
@Configuration
public class Config {
    @Bean
    public FinanceDocumentRepository financeDocumentRepository() {
        return new FinanceDocumentRepository();
    }
    @Bean
    public GrpcFinanceDataServer grpcFinanceDataServer(FinanceDocumentRepository financeDocumentRepository) {
        return new GrpcFinanceDataServer(financeDocumentRepository);
    }

}