package com.finance.riskfactormicroservice.services.grpc;

import com.finance.riskfactormicroservice.services.StockPriceService;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class DailyStockPriceServiceGRPC extends GreeterGrpc.GreeterImplBase {
    private StockPriceService stockPriceService=new StockPriceService();

//    @Override
//    public void getDailyStockPrice(DailyStockPriceRequest request, StreamObserver<DailyStockPriceResponse> responseObserver) {
//        String message = "Hello " + request.getName();
//
//
//        DailyStockPriceResponse response = DailyStockPriceResponse.newBuilder().setMessage(message).build();
//
//
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
@Override
public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
}

}
