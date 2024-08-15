package com.finance.basicassetallocation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;


public class AllocationRequest {

    @JsonProperty("symbols")
    private String[] symbols;

    // Getters and setters

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }
}
