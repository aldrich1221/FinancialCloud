package com.finance.basicassetallocation.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StockDataRequest {

    @JsonProperty("symbols")
    private String[] symbols;
    
    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

    // Getters and setters

    public String[] getSymbols() {
        return symbols;
    }
   
    public String getStartTime() {return startTime;}
    public String getEndTime() {return endTime;}

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }
   
    public void setStartTime(String startTime) {this.startTime = startTime;}
    public void setEndTime(String endTime) {this.endTime = endTime;}



}
