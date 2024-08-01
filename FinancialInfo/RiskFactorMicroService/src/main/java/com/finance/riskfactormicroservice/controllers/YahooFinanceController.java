package com.finance.riskfactormicroservice.controllers;


import com.finance.riskfactormicroservice.models.StockPriceRequestBody;
import com.finance.riskfactormicroservice.services.StockPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/yahoo/v1")
public class YahooFinanceController {
    private StockPriceService stockPriceService=new StockPriceService();

    @PostMapping(path= "/getStockPrices", consumes = "application/json", produces = "application/json")
    public String testConnecionPool(@RequestBody StockPriceRequestBody request) throws SQLException, IOException {

        String[] symbols=request.getSymbols();
        stockPriceService.getStockPricesBySymbols(symbols);
        return "Test OK!";

    }
}
