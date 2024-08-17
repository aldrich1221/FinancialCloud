package com.finance.dailystockdatamicroservice.controllers;


import com.finance.dailystockdatamicroservice.models.requests.StockPriceRequestBody;
import com.finance.dailystockdatamicroservice.models.response.ApiResponse;
import com.finance.dailystockdatamicroservice.models.response.IndicatorResponse;
import com.finance.dailystockdatamicroservice.services.StockPriceService;
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


    @PostMapping(path= "/get_daily_prices_with_date_range", consumes = "application/json", produces = "application/json")
    public ApiResponse<?> getDailyPrices(@RequestBody StockPriceRequestBody request) throws SQLException, IOException {
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
