package com.finance.dailystockdatamicroservice.services.grpc;


import com.finance.dailystockdatamicroservice.models.response.IndicatorResponse;
import com.finance.dailystockdatamicroservice.services.StockPriceService;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
//import io.grpc.examples.helloworld.GreeterGrpc;
//import io.grpc.examples.helloworld.HelloReply;
//import io.grpc.examples.helloworld.HelloRequest;

import io.grpc.grpcinterface.GetDataGrpc;
import io.grpc.grpcinterface.DataRequest;
import io.grpc.grpcinterface.DataResponse;

import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class FinanceDataServer {
    private static final Logger logger = Logger.getLogger(FinanceDataServer.class.getName());
    private static final StockPriceService stockPriceService=new StockPriceService();

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new FinanceDataImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    FinanceDataServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final FinanceDataServer server = new FinanceDataServer();
        server.start();
        server.blockUntilShutdown();
    }

    static class FinanceDataImpl extends GetDataGrpc.GetDataImplBase {


        @Override
        public void getData(DataRequest req, StreamObserver<DataResponse> responseObserver) throws IOException {
            System.out.println(req.getRequestData());
            String symbol=req.getRequestData();
            String startTime=req.getStartTime();
            String endTime=req.getEndTime();

            String[] list={symbol};

            IndicatorResponse ans = (IndicatorResponse) stockPriceService.getStockPricesBySymbolsByDateRange(list,startTime,endTime);


            ans.

            DataResponse reply = DataResponse.newBuilder()
                    .setMessage("Get Data.." + req.getRequestData())
                    .setStartTime(startTime)
                    .setEndTime(endTime)
                    .setNotes("Return data")
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}