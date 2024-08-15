package com.finance.basicassetallocation.services;

import com.finance.basicassetallocation.repositories.FinanceDocumentRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AssetAllocationService {

    private StockPriceService stockPriceService=new StockPriceService();
    private SimpleGeneticAlgorithm simpleGeneticlgorithm=new SimpleGeneticAlgorithm();

    public HashMap<String,Double> simpleAllocationSP500() throws IOException {
            System.out.println("Enter simple allocation SP500");
         String [] symbols={"AOS", "MMM", "ABT"};
         HashMap<String, HashMap<String, ArrayList<?>>> data=stockPriceService.getDailyIndicators(symbols);
         System.out.println(data);
        HashMap<String, Double> lastVolatility=(HashMap<String, Double>)getLastElements(data.get("Volatility"));
        HashMap<String, Double>lastRollingReturns=(HashMap<String, Double>)getLastElements(data.get("RollingReturns"));

        return simpleGeneticlgorithm.simpleGeneticAlgorithm(lastRollingReturns,lastVolatility);

    }

    public static Map<String, Double> getLastElements(HashMap<String, ArrayList<?>> map) {
        Map<String,  Double> lastElements = new HashMap<>();

        for (Map.Entry<String, ArrayList<?>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<?> list = entry.getValue();

            if (list != null && !list.isEmpty()) {
                Double lastElement = (Double) list.get(list.size() - 1);
                lastElements.put(key, lastElement);
            } else {
                lastElements.put(key, null); // Handle empty lists
            }
        }

        return lastElements;
    }
}
