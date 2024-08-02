package com.finance.riskfactormicroservice.models;

public class StockPriceRequestBody {
    private String [] symbols;

   public void setSymbols(String[] symbols){
       this.symbols = symbols;
   }

   public String[] getSymbols(){
       return symbols;
   }
}
