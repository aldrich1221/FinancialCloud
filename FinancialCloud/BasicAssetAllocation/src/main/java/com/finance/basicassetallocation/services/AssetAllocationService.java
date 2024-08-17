package com.finance.basicassetallocation.services;

import com.finance.basicassetallocation.algorithms.SimpleGeneticAlgorithm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AssetAllocationService {

    private StockPriceService stockPriceService=new StockPriceService();
    private SimpleGeneticAlgorithm simpleGeneticlgorithm=new SimpleGeneticAlgorithm();

    public HashMap<String,Double> simpleAllocation(String [] symbols) throws IOException {

         HashMap<String, HashMap<String, ArrayList<?>>> data=stockPriceService.getDailyIndicators(symbols);
//         System.out.println(data);
        HashMap<String, Double> lastVolatility=new HashMap();
        HashMap<String, Double> lastRollingReturns=new HashMap();
         for(String symbol:symbols){
             Map<String, Object> lastIndicatorBySymbol=getLastElements(data.get(symbol));
             lastVolatility.put(symbol, (Double) lastIndicatorBySymbol.get("Volatility"));
             lastRollingReturns.put(symbol, (Double) lastIndicatorBySymbol.get("RollingReturns"));
         }

        return simpleGeneticlgorithm.simpleGeneticAlgorithm(lastRollingReturns,lastVolatility);

    }

    public static Map<String, Object> getLastElements(HashMap<String, ArrayList<?>> map) {
        Map<String,  Object> lastElements = new HashMap<>();

        for (Map.Entry<String, ArrayList<?>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<?> list = entry.getValue();

            if (list != null && !list.isEmpty()) {
                Object lastElement = list.get(list.size() - 1);
                lastElements.put(key, lastElement);
            } else {
                lastElements.put(key, null); // Handle empty lists
            }
        }

        return lastElements;
    }
}
