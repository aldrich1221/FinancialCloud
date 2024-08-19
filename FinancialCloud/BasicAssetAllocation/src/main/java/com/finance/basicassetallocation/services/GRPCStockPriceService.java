package com.finance.basicassetallocation.services;



import com.finance.basicassetallocation.grpc.FinanceDataClient;
import com.finance.basicassetallocation.models.mongoDB.DailyStockPriceDocument;
import com.finance.basicassetallocation.repositories.FinanceDocumentRepository;
import com.finance.basicassetallocation.serviceUtils.IndicatorUtil;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.finance.basicassetallocation.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;


public class GRPCStockPriceService {
//    private MongoFinanceRepository financeRepository=new MongoFinanceRepository();


    public HashMap<String, HashMap<String, ArrayList<?>>> getStockPricesBySymbolsByDateRange(String [] symbols, Timestamp startTime, Timestamp endTime) throws IOException, InterruptedException {
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String startDate=dateFormat.format(new Date(startTime.getTime()));
        String endDate=dateFormat.format(new Date(endTime.getTime()));

        List<String>symbolsList=Arrays.stream(symbols).toList();
        String user = "Aldrich";
        // Access a service running on the local machine on port 50051
        String target = "localhost:50051";
        // Allow passing in the user and target strings as command line arguments


        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        String response=null;
        try {
            FinanceDataClient client = new FinanceDataClient(channel);
            response=client.getData(symbolsList,startDate,endDate);
        } finally {

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }


        for (String symbol : symbols) {

            String startId=symbol+"_"+startDate;
            String endId=symbol+"_"+endDate;

//            List<DailyStockPriceDocument> dailyPrices=financeDocumentRepository.findDocumentsInRange(startId,endId);
//
//            ArrayList<Date> datelists =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getDate);
//
//            ArrayList<Double> adjClosePrices =extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getAdjClose);
//
//            HashMap<String,ArrayList<?>> symbolIndicator= new HashMap<>();
//            symbolIndicator.put("Date",datelists);
//            symbolIndicator.put("AdjPrice",adjClosePrices);


//            nestedMap.put(symbol,symbolIndicator);
        }
        return nestedMap;

    }








}
