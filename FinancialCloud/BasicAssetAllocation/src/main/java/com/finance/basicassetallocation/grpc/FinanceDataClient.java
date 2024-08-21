package com.finance.basicassetallocation.grpc;


import io.grpc.*;

import io.grpc.grpcinterface.GetDataGrpc;
import io.grpc.grpcinterface.DataRequest;
import io.grpc.grpcinterface.DataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FinanceDataClient {
    private static final Logger logger = Logger.getLogger(FinanceDataClient.class.getName());

    private final GetDataGrpc.GetDataBlockingStub blockingStub;

    /** Construct client for accessing HelloWorld server using the existing channel. */
    public FinanceDataClient(Channel channel) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
        blockingStub = GetDataGrpc.newBlockingStub(channel);
    }

    /** Say hello to server. */
    public String getData(List<String> symbols , String startTimeString, String endTimeString) {
        String requestData=symbols.stream().toString();
        logger.info("Will try to get " + requestData + " ...");
        DataRequest request = DataRequest.newBuilder()
                .setRequestData(requestData)
                .setStartTime(startTimeString)
                .setEndTime( endTimeString)
                .setNotes("")
                .build();
        DataResponse response;
        try {
            response = blockingStub.getData(request);

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        logger.info("Obtain: " + response.getMessage());
        return response.getMessage();
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
   
}