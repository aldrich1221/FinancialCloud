package com.finance.riskfactormicroservice.services.grpc;

import com.finance.riskfactormicroservice.services.StockPriceService;

@GrpcService
public class DailyStockPriceServiceGRPC {
    private StockPriceService stockPriceService=new StockPriceService();

    @Override
    public void getDailyStockPrice(DailyStockPriceRequest request, StreamObserver<DailyStockPriceResponse> responseObserver) {
        String message = "Hello " + request.getName();


        DailyStockPriceResponse response = DailyStockPriceResponse.newBuilder().setMessage(message).build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
