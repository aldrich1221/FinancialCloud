package com.finance.riskfactormicroservice.models.requests;

public class StockPriceRequestBody {
    private String [] symbols;

    private Timestamp startTime;
    private Timestamp endTime;


   public void setSymbols(String[] symbols){
       this.symbols = symbols;
   }

   public String[] getSymbols(){
       return symbols;
   }

   public void setStartTime(Timestamp startTime){
       this.startTime = startTime;
   }
   public Timestamp getStartTime(){
       return startTime;
   }
   public void setEndTime(Timestamp endTime){
       this.endTime = endTime;
   }
   public Timestamp getEndTime(){
       return endTime;
   }
}
