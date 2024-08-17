package com.finance.basicassetallocation.serviceUtils;



import com.finance.basicassetallocation.models.mongoDB.DailyStockPriceDocument;

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

//    public static ArrayList<Double> volatility(ArrayList<Double> dataList,int windowSize){
//
//        if (dataList.size()<windowSize){
//            throw new IllegalArgumentException("dataList is less than windowSize");
//        }
//        ArrayList<Double> Ans=new ArrayList<>();
//        for(int i =0;i<dataList.size();i++){
//            if(i<windowSize){
//                Ans.add(null);
//                continue;
//            }
//            List<Double> subList = dataList.subList(i, i+ windowSize);
//            Ans.add(subList.stream().mapToDouble(Double::doubleValue).average().orElseGet(null));
//        }
//        return Ans;
//    }

    public static ArrayList<Double> Volatility(ArrayList<Double> timeSeries, int windowSize) {
        ArrayList<Double> volatilities = new ArrayList<>();

        for (int i = 0; i <= timeSeries.size()-windowSize ; i++) {
            if(i<windowSize){
                volatilities.add(null);
                continue;
            }
            double[] window = new double[windowSize];

            // Extract the window data
            for (int j = 0; j < windowSize; j++) {
                window[j] = timeSeries.get(i+ j);
            }

            // Calculate returns for the window
            double[] returns = calculateReturns(window);

            // Calculate the standard deviation (volatility) of the returns
            double volatility = calculateStandardDeviation(returns);

            volatilities.add(volatility);
        }

        return volatilities;
    }

    public static ArrayList<Double> RollingReturns(ArrayList<Double> timeSeries, int windowSize) {
        ArrayList<Double> rollingReturns = new ArrayList<>();

        for (int i = 0; i <= timeSeries.size() - windowSize; i++) {
            if(i<windowSize){
                rollingReturns.add(null);
                continue;
            }
            double initialPrice = timeSeries.get(i);
            double finalPrice = timeSeries.get(i + windowSize - 1);

            // Calculate return for the window
            double rollingReturn = (finalPrice - initialPrice) / initialPrice;

            rollingReturns.add(rollingReturn);
        }

        return rollingReturns;
    }

    public static double[] calculateReturns(double[] window) {
        double[] returns = new double[window.length - 1];

        for (int i = 0; i < window.length - 1; i++) {
            returns[i] = (window[i + 1] - window[i]) / window[i];
        }

        return returns;
    }

    public static double calculateStandardDeviation(double[] returns) {
        double mean = calculateMean(returns);
        double sumSquaredDifferences = 0.0;

        for (double r : returns) {
            sumSquaredDifferences += Math.pow(r - mean, 2);
        }

        return Math.sqrt(sumSquaredDifferences / (returns.length - 1));
    }

    public static double calculateMean(double[] returns) {
        double sum = 0.0;

        for (double r : returns) {
            sum += r;
        }

        return sum / returns.length;
    }




}