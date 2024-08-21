package com.finance.basicassetallocation.services;



import com.finance.basicassetallocation.grpc.FinanceDataClient;
import com.finance.basicassetallocation.models.mongoDB.DailyStockPriceDocument;
import com.finance.basicassetallocation.repositories.FinanceDocumentRepository;
import com.finance.basicassetallocation.serviceUtils.IndicatorUtil;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.finance.basicassetallocation.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;


@Service
public class GRPCStockPriceService {

    public HashMap<String, HashMap<String, ArrayList<?>>> getStockPricesBySymbolsByDateRange(String [] symbols, Timestamp startTime, Timestamp endTime) throws IOException, InterruptedException {
        HashMap<String, HashMap<String, ArrayList<?>>> nestedMap=new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String startDate=dateFormat.format(new Date(startTime.getTime()));
        String endDate=dateFormat.format(new Date(endTime.getTime()));

        List<String>symbolsList=Arrays.stream(symbols).toList();

//        String target = grpcServiceStockDataUri;
        System.out.println("Start to build channel");
        ManagedChannel channel = Grpc.newChannelBuilder("localhost:50051", InsecureChannelCredentials.create())
                .build();
        for (String symbol : symbols) {
            String response = null;
            List<String> listSymbol=new ArrayList<>();
            listSymbol.add(symbol);
            try {
                FinanceDataClient client = new FinanceDataClient(channel);
                System.out.println("Start to getData");
                response = client.getData(symbol, startDate, endDate);
            } finally {

                channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            }
            System.out.println("Get: "+response);
        }




////            nestedMap.put(symbol,symbolIndicator);
//        }
        return nestedMap;

    }








}
