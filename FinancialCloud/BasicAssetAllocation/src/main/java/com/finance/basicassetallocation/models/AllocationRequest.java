package com.finance.basicassetallocation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;


public class AllocationRequest {

    @JsonProperty("symbols")
    private String[] symbols;

    private String method;

    // Getters and setters

    public String[] getSymbols() {
        return symbols;
    }
    public String getMethod() {
        return method;
    }

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }
    public void setMethod(String method) {
        this.method = method;
    }
}
