package com.finance.dailystockdatamicroservice.models.requests;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Setter
@Getter
public class StockPriceRequestBody {
    private String [] symbols;

    private Timestamp startTime;
    private Timestamp endTime;


   public void setSymbols(String[] symbols){
       this.symbols = symbols;
   }

    public void setStartTime(Timestamp startTime){
       this.startTime = startTime;
   }

    public void setEndTime(Timestamp endTime){
       this.endTime = endTime;
   }
}
