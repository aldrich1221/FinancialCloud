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
    public static void main(String[] args) throws Exception {
        String user = "Aldrich";
        List<String> symbols = new ArrayList<>();
        symbols.add(user);
        // Access a service running on the local machine on port 50051
        String target = "localhost:50051";
        // Allow passing in the user and target strings as command line arguments
        if (args.length > 0) {
            if ("--help".equals(args[0])) {
                System.err.println("Usage: [name [target]]");
                System.err.println("");
                System.err.println("  name    The name you wish to be greeted by. Defaults to " + user);
                System.err.println("  target  The server to connect to. Defaults to " + target);
                System.exit(1);
            }
            user = args[0];
        }
        if (args.length > 1) {
            target = args[1];
        }

        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();
        try {
            FinanceDataClient client = new FinanceDataClient(channel);
            client.getData(symbols,"111","222");
        } finally {

            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}