package com.finance.riskfactormicroservice.repositories;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YahooFinanceRepository {


    public void getYahooStockPricesBySymbols(String[] symbols) throws IOException {
        Map<String, Stock> stocks = (Map<String, Stock>) YahooFinance.get(symbols, true);
        for (Stock stock : stocks.values()) {
            System.out.println("stock");
            System.out.println(stock);
            Stock stockdata = stocks.get(stock);
            List<HistoricalQuote> historicalQuotes=stockdata.getHistory();

            System.out.println(historicalQuotes);
        }


    }
}
