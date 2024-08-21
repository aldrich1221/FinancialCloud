package com.finance.dailystockdatamicroservice.services;


import com.finance.dailystockdatamicroservice.models.mongoDB.DailyStockPriceDocument;
import com.finance.dailystockdatamicroservice.repositories.FinanceDocumentRepository;

import java.io.IOException;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.finance.dailystockdatamicroservice.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;

public class StockPriceService {
//    private MongoFinanceRepository financeRepository=new MongoFinanceRepository();
    private FinanceDocumentRepository financeDocumentRepository=new FinanceDocumentRepository();


    public HashMap<String, HashMap<String, ArrayList<?>>> getStockPricesBySymbolsByDateRange(String [] symbols, String startDate, String endDate) throws IOException {
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        String startDate=dateFormat.format(new Date(startTime.getTime()));
//        String endDate=dateFormat.format(new Date(endTime.getTime()));


        for (String symbol : symbols) {

            String startId=symbol+"_"+startDate;
            String endId=symbol+"_"+endDate;

            List<DailyStockPriceDocument> dailyPrices=financeDocumentRepository.findDocumentsInRange(startId,endId);

            ArrayList<Date> datelists =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getDate);

            ArrayList<Double> adjClosePrices =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getAdjClose);

            HashMap<String,ArrayList<?>> symbolIndicator= new HashMap<>();
            symbolIndicator.put("Date",datelists);
            symbolIndicator.put("AdjPrice",adjClosePrices);


            nestedMap.put(symbol,symbolIndicator);
        }
        return nestedMap;

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
