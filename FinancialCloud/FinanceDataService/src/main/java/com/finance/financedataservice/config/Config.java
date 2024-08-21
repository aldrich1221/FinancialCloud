package com.finance.financedataservice.config;
import com.finance.financedataservice.repositories.FinanceDocumentRepository;
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
}