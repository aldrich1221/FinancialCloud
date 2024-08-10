package com.finance.basicassetallocation.services;

import java.util.*;
import java.util.stream.Collectors;

public class AssetAllocationService {

    private static double fitness(HashMap<String, Double> portfolio, HashMap<String, Double> returns, HashMap<String, Double> risk, ArrayList<String> symbols) {
        return symbols.stream()
                .mapToDouble(symbol -> portfolio.get(symbol) * returns.get(symbol) - portfolio.get(symbol) * risk.get(symbol))
                .sum();
    }
    private HashMap<String, Double> mutate(HashMap<String, Double> portfolio,ArrayList<String> symbols){
        Random random = new Random();
        int index=random.nextInt(symbols.size());
        portfolio.put(symbols.get(index), random.nextDouble());
        return portfolio;
    }


    public static HashMap<String, Double> crossover(HashMap<String, Double> portfolio1, HashMap<String, Double> portfolio2,ArrayList<String> symbols) {
        Random random = new Random();
        int index = random.nextInt(portfolio1.size());
        HashMap<String, Double> newPortfolio = new HashMap<String, Double>();
        List<String>sub1=symbols.subList(0,index);
        List<String>sub2=symbols.subList(index,portfolio2.size());
        sub1.forEach(symbol->newPortfolio.put(symbol,portfolio1.get(symbol)));
        sub2.forEach(symbol->newPortfolio.put(symbol,portfolio2.get(symbol)));


        return newPortfolio;
    }

    public  List<HashMap<String,Double>> randomPopulation(int populationSize,int returnsSize,ArrayList<String> symbols){
        Random random = new Random();
        List<HashMap<String,Double>> population = new ArrayList<>();
        for(int i=0;i<populationSize;i++){
            HashMap<String,Double> individual = new HashMap<>();
            symbols.forEach(symbol->individual.put(symbol,random.nextDouble()));
            population.add(individual);
        }
        return population;
    }


    public HashMap<String,Double>simpleGeneticAlgorithm(HashMap<String,Double>returns,HashMap<String,Double> risk,int populationSize,int generations){
        Random random = new Random();
        HashMap<String, Double> portfolio = new HashMap<>();
        ArrayList<String> symbols= (ArrayList<String>) returns.keySet();
        List<HashMap<String,Double>> population=randomPopulation(populationSize,returns.size(), symbols);

        for (int gen = 0; gen < generations; gen++) {
            population.sort(Comparator.comparingDouble(ind -> -fitness(ind, returns, risk, symbols)));
            List<HashMap<String, Double>> newPopulation = new ArrayList<>(population.subList(0, 2));
            for (int i = 0; i < populationSize - 2; i++) {
                List<Double> parent1 = population.get(random.nextInt(5));
                List<Double> parent2 = population.get(random.nextInt(5));
                List<Double> child = mutate(crossover(parent1, parent2));
                newPopulation.add(child);
            }
            population=newPopulation;
        }


        return Collections.max(population,Comparator.comparingDouble(ind -> -fitness(ind, returns, risk, symbols)));

    }

    public HashMap<String,Double>simpleGeneticAlgorithm(HashMap<String,Double>returns,HashMap<String,Double> risk){

        return simpleGeneticAlgorithm(returns,risk,10,100);
    }



}
