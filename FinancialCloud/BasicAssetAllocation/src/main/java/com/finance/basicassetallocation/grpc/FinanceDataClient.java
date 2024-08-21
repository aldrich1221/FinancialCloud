package com.finance.basicassetallocation.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.grpcinterface.DataRequest;
import io.grpc.grpcinterface.DataResponse;
import io.grpc.grpcinterface.GetDataGrpc;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinanceDataClient {
    private static final Logger logger = Logger.getLogger(FinanceDataClient.class.getName());

    private final ManagedChannel channel;
    private final GetDataGrpc.GetDataBlockingStub blockingStub;

    /** Construct client connecting to the gRPC server at {@code host:port}. */
    public FinanceDataClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()  // Use plaintext for simplicity, disable in production
                .build());
    }

    /** Construct client for accessing the server using the existing channel. */
    public FinanceDataClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GetDataGrpc.newBlockingStub(channel);
    }

    /** Shutdown the client channel gracefully. */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Get financial data from the server. */
    public String getData(String symbol, String startTime, String endTime) {
        logger.info("Requesting data for symbol: " + symbol);
        DataRequest request = DataRequest.newBuilder()
                .setRequestData(symbol)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .build();

        DataResponse response = null;
        try {
            response = blockingStub.getData(request);
            logger.info("Data received: " + response.getMessage());
            logger.info("Data received: " + response.getNotes());
            logger.info("List of data: " + response.getListData().getValueList());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response.getMessage();
    }

    public static void main(String[] args) throws Exception {
        FinanceDataClient client = new FinanceDataClient("localhost", 50051);
        try {
            // Replace these values with actual data
            String symbol = "AAPL";
            String startTime = "2024-01-01";
            String endTime = "2024-01-31";

            client.getData(symbol, startTime, endTime);
        } finally {
            client.shutdown();
        }
    }
}