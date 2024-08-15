package com.finance.basicassetallocation.services;



import com.finance.basicassetallocation.models.mongoDB.DailyStockPriceDocument;
import com.finance.basicassetallocation.repositories.FinanceDocumentRepository;
import com.finance.basicassetallocation.serviceUtils.IndicatorUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.finance.basicassetallocation.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;


public class StockPriceService {
//    private MongoFinanceRepository financeRepository=new MongoFinanceRepository();
    private FinanceDocumentRepository financeDocumentRepository=new FinanceDocumentRepository();


    public HashMap<String, HashMap<String, ArrayList<?>>> getStockPricesBySymbolsByDateRange(String [] symbols, Timestamp startTime, Timestamp endTime) throws IOException {
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String startDate=dateFormat.format(new Date(startTime.getTime()));
        String endDate=dateFormat.format(new Date(endTime.getTime()));


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
        System.out.println("Go get data.."+symbols.length);
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();

        for (String symbol : symbols) {
            List<DailyStockPriceDocument> dailyPrices=financeDocumentRepository.findAllDocuments(symbol);
            System.out.println("dailyPrices.."+dailyPrices);
            ArrayList<Date> datelists =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getDate);

            ArrayList<Double> adjClosePrices =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getAdjClose);
            ArrayList<Double> volatility= IndicatorUtil.Volatility(adjClosePrices,30);
            ArrayList<Double> rollingReturns= IndicatorUtil.RollingReturns(adjClosePrices,30);

//            ArrayList<Double> MA10=IndicatorUtil.movingAverage(adjClosePrices,10);
//            ArrayList<Double> MA30=IndicatorUtil.movingAverage(adjClosePrices,30);

            HashMap<String,ArrayList<?>> symbolIndicator= new HashMap<>();
            symbolIndicator.put("Date",datelists);
            symbolIndicator.put("AdjPrice",adjClosePrices);
            symbolIndicator.put("Volatility",volatility);
            symbolIndicator.put("RollingReturns",rollingReturns);

//            symbolIndicator.put("MA10",MA10);
//            symbolIndicator.put("MA30",MA30);


            nestedMap.put(symbol,symbolIndicator);
        }
        return nestedMap;

    }




}
