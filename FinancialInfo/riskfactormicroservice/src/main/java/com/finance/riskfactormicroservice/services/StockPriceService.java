package com.finance.riskfactormicroservice.services;


import com.finance.riskfactormicroservice.models.mongoDB.DailyStockPriceDocument;
import com.finance.riskfactormicroservice.repositories.FinanceDocumentRepository;
import com.finance.riskfactormicroservice.repositories.MongoFinanceRepository;
import com.finance.riskfactormicroservice.serviceUtils.IndicatorUtil;

import java.io.IOException;


import java.util.*;

import static com.finance.riskfactormicroservice.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;

public class StockPriceService {
    private MongoFinanceRepository financeRepository=new MongoFinanceRepository();
    private FinanceDocumentRepository financeDocumentRepository=new FinanceDocumentRepository();



    public void getStockPricesBySymbols(String [] symbols) throws IOException {
//        financeRepository.getMongoStockPriceSchemaBySymbol(symbols);
        Set<String> ans=financeDocumentRepository.getCollectionSchema("SPY");
        ans.forEach(System.out::println);
                List<DailyStockPriceDocument> dailyPrices=financeDocumentRepository.findAllDocuments("SPY");
        for (DailyStockPriceDocument dailyPrice : dailyPrices) {
           System.out.println(dailyPrice.getClose()+dailyPrice.getDate().toString());

        }
    }

    public HashMap<String, HashMap<String, ArrayList<?>>> getDailyIndicators(String [] symbols) throws IOException {
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();

        for (String symbol : symbols) {
            List<DailyStockPriceDocument> dailyPrices=financeDocumentRepository.findAllDocuments(symbol);

            ArrayList<Date> datelists =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getDate);

            ArrayList<Double> adjClosePrices =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getAdjClose);

//            ArrayList<Double> MA10=IndicatorUtil.movingAverage(adjClosePrices,10);
//            ArrayList<Double> MA30=IndicatorUtil.movingAverage(adjClosePrices,30);

            HashMap<String,ArrayList<?>> symbolIndicator= new HashMap<>();
            symbolIndicator.put("Date",datelists);
            symbolIndicator.put("AdjPrice",adjClosePrices);

//            symbolIndicator.put("MA10",MA10);
//            symbolIndicator.put("MA30",MA30);


            nestedMap.put(symbol,symbolIndicator);
        }
        return nestedMap;

    }




}
