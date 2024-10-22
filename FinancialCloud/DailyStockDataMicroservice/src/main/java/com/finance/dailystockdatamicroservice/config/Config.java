package com.finance.dailystockdatamicroservice.config;

import com.finance.dailystockdatamicroservice.repositories.FinanceDocumentRepository;
import com.finance.dailystockdatamicroservice.services.grpc.FinanceDataServer;
import org.springframework.beans.factory.annotation.Autowired;
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