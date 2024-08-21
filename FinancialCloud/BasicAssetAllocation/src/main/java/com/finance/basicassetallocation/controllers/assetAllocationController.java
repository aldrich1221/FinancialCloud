package com.finance.basicassetallocation.controllers;

import com.finance.basicassetallocation.models.AllocationRequest;
import com.finance.basicassetallocation.models.response.ApiResponse;
import com.finance.basicassetallocation.services.AssetAllocationService;
//import com.finance.basicassetallocation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

import static com.finance.basicassetallocation.serviceUtils.StockPoolUtil.SP500Symbols;
import static com.finance.basicassetallocation.serviceUtils.StockPoolUtil.checkValid;

@RestController
@RequestMapping("/api/v1/allocation")
public class assetAllocationController {

    @Autowired
    private AssetAllocationService assetAllocationService;

    @Autowired
    private GRPCStockPriceService grpcStockPriceService;
    
    @GetMapping("/test")
    public String test() {
        try {
            return "Welcome to test";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/ping")
    public String ping() {
        try {
            return "Welcome to ping";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping(path="/basic",consumes = "application/json",produces = "application/json")
    public ApiResponse<?> basicMethod(@RequestBody AllocationRequest allocationRequest) throws IOException {
        String[] symbols = allocationRequest.getSymbols();
        HashMap<String,Double> ans=new HashMap<>();

        try {
            if(symbols[0].equals("SP500") && symbols.length == 1){
                    System.out.println("Enter simple allocation SP500");

                    ans =assetAllocationService.simpleAllocation(SP500Symbols);
                    System.out.println(ans);

            }else if(checkValid(symbols)){

                ans =assetAllocationService.simpleAllocation(symbols);
            }
            else{
                throw new Exception("Invalid symbols");
            }

            ApiResponse<HashMap<String,Double>> response = new ApiResponse<>(200, "Success", ans);
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Exception> response = new ApiResponse<>(300, "Failure", e);
            return response;
        }
    }

    @PostMapping(path="/getPrice",consumes = "application/json",produces = "application/json")
    public ApiResponse<?> basicMethod(@RequestBody StockPriceRequest stockPriceRequest) throws IOException {
        String[] symbols = stockPriceRequest.getSymbols();
        String startTime=stockPriceRequest.getStartTime();
        String endTime=stockPriceRequest.getEndTime();
        HashMap<String,Double> ans=new HashMap<>();

        try {
            if(checkValid(symbols)){

                ans =grpcStockPriceService.getStockPricesBySymbolsByDateRange(symbols,startTime,endTime);
            }
            else{
                throw new Exception("Invalid symbols");
            }

            ApiResponse<HashMap<String,Double>> response = new ApiResponse<>(200, "Success", ans);
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Exception> response = new ApiResponse<>(300, "Failure", e);
            return response;
        }
    }


}
