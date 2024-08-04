package com.finance.riskfactormicroservice.controllers;


import com.finance.riskfactormicroservice.models.requests.StockPriceRequestBody;
import com.finance.riskfactormicroservice.models.response.ApiResponse;
import com.finance.riskfactormicroservice.models.response.IndicatorResponse;
import com.finance.riskfactormicroservice.services.StockPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/finance/v1")
public class FinanceController {
    private StockPriceService stockPriceService=new StockPriceService();

    @PostMapping(path= "/getStockPrices", consumes = "application/json", produces = "application/json")
    public String testConnecionPool(@RequestBody StockPriceRequestBody request) throws SQLException, IOException {

        String[] symbols=request.getSymbols();
        System.out.println(symbols);
        stockPriceService.getStockPricesBySymbols(symbols);
        return "Test OK!";

    }

    @PostMapping(path= "/get_daily_indicators", consumes = "application/json", produces = "application/json")
    public ApiResponse<?> getDailyIndicators(@RequestBody StockPriceRequestBody request) throws SQLException, IOException {
        try {
            String[] symbols = request.getSymbols();
            IndicatorResponse ans = (IndicatorResponse) stockPriceService.getDailyIndicators(symbols);

            ApiResponse<IndicatorResponse> response = new ApiResponse<>(200, "Success", ans);
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Exception> response = new ApiResponse<>(300, "Failure", e);
            return response;
        }


    }
}
