package com.finance.financedataservice.utils;


import com.finance.financedataservice.models.mongodb.DailyStockPriceDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IndicatorUtil {
    // Private static instance of the class
    private static volatile IndicatorUtil instance;

    // Private constructor to prevent instantiation
    private IndicatorUtil() {}

    // Public method to provide access to the instance
    public static IndicatorUtil getInstance() {
        if (instance == null) {
            synchronized (IndicatorUtil.class) {
                if (instance == null) {
                    instance = new IndicatorUtil();
                }
            }
        }
        return instance;
    }

    public static <T> ArrayList<T> extractDailyDataToArrayList(List<DailyStockPriceDocument> data, Function<DailyStockPriceDocument, T> function) {
        return data.stream()
                .map(function)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Double> movingAverage(ArrayList<Double> dataList,int windowSize){

        if (dataList.size()<windowSize){
            throw new IllegalArgumentException("dataList is less than windowSize");
        }
        ArrayList<Double> Ans=new ArrayList<>();
        for(int i =0;i<dataList.size();i++){
              if(i<windowSize){
                  Ans.add(null);
                  continue;
              }
              List<Double> subList = dataList.subList(i, i+ windowSize);
              Ans.add(subList.stream().mapToDouble(Double::doubleValue).average().orElseGet(null));
          }
        return Ans;
    }




}