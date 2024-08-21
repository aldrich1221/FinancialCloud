package com.finance.financedataservice.models.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class DailyStockPriceDocument {
    @Id
    @Field(name = "_id")
    private String id;

    @Field(name = "Open")
    private Double open;

    @Field(name = "High")
    private Double high;
    @Field(name = "Low")
    private Double low;
    @Field(name = "Close")
    private Double close;
    @Field(name = "Volume")
    private long volume;
    @Field(name = "Adj Close")
    private Double adjClose;

    @Field(name = "Date")
    private java.util.Date Date;

    public Double getHigh() {
        return high;
    }
    public void setHigh(Double high) {
        this.high = high;
    }
    public Double getLow() {
        return low;
    }
    public void setLow(Double low) {
        this.low = low;
    }
    public Double getClose() {
        return close;
    }
    public void setClose(Double close) {
        this.close = close;
    }
    public long getVolume() {
        return volume;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }
    public Double getAdjClose() {
        return adjClose;
    }
    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public java.util.Date getDate() {
        return Date;
    }
    public void setDate(java.util.Date date) {
        Date = date;
    }
    public Double getOpen() {
        return open;
    }
    public void setOpen(Double open) {
        this.open = open;
    }

}
