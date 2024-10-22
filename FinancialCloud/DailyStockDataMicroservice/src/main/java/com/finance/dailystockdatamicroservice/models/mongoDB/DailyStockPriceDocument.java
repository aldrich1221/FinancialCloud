package com.finance.dailystockdatamicroservice.models.mongoDB;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

@Setter
@Getter
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

    public DailyStockPriceDocument() {}

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public void setHigh(Double high) {
        this.high = high;

    }

    public void setLow(Double low) {
        this.low = low;

    }

    public void setClose(Double close) {
        this.close = close;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Timestamp getDate2Timestamp(){
        return new Timestamp(Date.getTime());
    }


}
