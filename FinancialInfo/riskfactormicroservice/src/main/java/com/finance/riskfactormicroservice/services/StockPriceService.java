package com.finance.riskfactormicroservice.services;


import com.finance.riskfactormicroservice.repositories.YahooFinanceRepository;

import java.io.IOException;

public class StockPriceService {
    private YahooFinanceRepository financeRepository=new YahooFinanceRepository();
    public void getStockPricesBySymbols(String [] symbols) throws IOException {
        financeRepository.getYahooStockPricesBySymbols(symbols);

    }

}
