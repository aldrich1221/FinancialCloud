package com.finance.basicassetallocation.algorithms;

import org.ejml.simple.SimpleMatrix;

import java.util.*;

public class MarkowitzAlgorithm {

    public HashMap<String,Double> simpleMarkowitzAlgorithm(HashMap<String,Double>returns, HashMap<String,HashMap<String,Double>> cov,ArrayList<String>symbols, double riskFreeRate){

        HashMap<String,Double> portfolio=new HashMap<>();
        ArrayList<Double> expectedReturnsList = new ArrayList<>();
        for(String symbol:symbols){
            expectedReturnsList.add(returns.get(symbol));
        }
        ArrayList<ArrayList<Double>> covarianceMatrixList = new ArrayList<>();
        for(String symbol1:symbols){
            ArrayList<Double> row = new ArrayList<>();
            for(String symbol2:symbols){
                row.add(cov.get(symbol1).get(symbol2));
            }
            covarianceMatrixList.add(row);
        }



         // Risk-free rate

        // Convert ArrayLists to EJML matrices
        SimpleMatrix expectedReturnsMatrix = new SimpleMatrix(expectedReturnsList.size(), 1);
        for (int i = 0; i < expectedReturnsList.size(); i++) {
            expectedReturnsMatrix.set(i, 0, expectedReturnsList.get(i));
        }

        SimpleMatrix covarianceMatrixMatrix = new SimpleMatrix(covarianceMatrixList.size(), covarianceMatrixList.size());
        for (int i = 0; i < covarianceMatrixList.size(); i++) {
            for (int j = 0; j < covarianceMatrixList.get(i).size(); j++) {
                covarianceMatrixMatrix.set(i, j, covarianceMatrixList.get(i).get(j));
            }
        }

        // Perform the optimization
        SimpleMatrix optimalWeights = optimizePortfolio(expectedReturnsMatrix, covarianceMatrixMatrix, riskFreeRate);

        // Print the optimal weights
        System.out.println("Optimal Weights: ");
        for (int i = 0; i < optimalWeights.numRows(); i++) {
            System.out.println("Asset " + (i + 1) + ": " + optimalWeights.get(i, 0));
            portfolio.put(symbols.get(i), optimalWeights.get(i, 0));
        }

        return portfolio;
    }

    public static SimpleMatrix optimizePortfolio(SimpleMatrix expectedReturns, SimpleMatrix covarianceMatrix, double riskFreeRate) {
        int numAssets = expectedReturns.numRows();

        // Add a row and column of ones to the covariance matrix
        SimpleMatrix ones = new SimpleMatrix(numAssets, 1);
        for (int i = 0; i < numAssets; i++) {
            ones.set(i, 0, 1.0);
        }

        SimpleMatrix augmentedMatrix = new SimpleMatrix(numAssets + 1, numAssets + 1);
        augmentedMatrix.insertIntoThis(0, 0, covarianceMatrix);
        augmentedMatrix.insertIntoThis(numAssets, 0, ones.transpose());
        augmentedMatrix.insertIntoThis(0, numAssets, ones);
        augmentedMatrix.set(numAssets, numAssets, 0.0);

        // Construct the b vector
        SimpleMatrix b = new SimpleMatrix(numAssets + 1, 1);
        b.insertIntoThis(0, 0, expectedReturns.minus(riskFreeRate));
        b.set(numAssets, 0, 1.0);

        // Solve the system of linear equations
        SimpleMatrix weights = augmentedMatrix.solve(b);

        // Extract the weights from the solution
        SimpleMatrix optimalWeights = weights.extractMatrix(0, numAssets, 0, 1);

        return optimalWeights;
    }
}
