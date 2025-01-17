package com.finance.dailystockdatamicroservice.services.grpc;


import com.finance.dailystockdatamicroservice.AppConfig;
import com.finance.dailystockdatamicroservice.config.Config;
import com.finance.dailystockdatamicroservice.models.mongoDB.DailyStockPriceDocument;
import com.finance.dailystockdatamicroservice.repositories.FinanceDocumentRepository;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
//import io.grpc.examples.helloworld.GreeterGrpc;
//import io.grpc.examples.helloworld.HelloReply;
//import io.grpc.examples.helloworld.HelloRequest;

import io.grpc.grpcinterface.GetDataGrpc;
import io.grpc.grpcinterface.DataRequest;
import io.grpc.grpcinterface.DataResponse;

import io.grpc.grpcinterface.ListOfString;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.finance.dailystockdatamicroservice.serviceUtils.IndicatorUtil.extractDailyDataToArrayList;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
@Service
public class FinanceDataServer {
    private static final Logger logger = Logger.getLogger(FinanceDataServer.class.getName());

    private final FinanceDocumentRepository financeDocumentRepository;
    private Server server;

    @Autowired
    public FinanceDataServer(FinanceDocumentRepository financeDocumentRepository) {
        this.financeDocumentRepository = financeDocumentRepository;
    }

    public void start() throws IOException {
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(new FinanceDataImpl(financeDocumentRepository))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                FinanceDataServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        FinanceDataServer server = context.getBean(FinanceDataServer.class);
        server.start();
        server.blockUntilShutdown();
    }

    static class FinanceDataImpl extends GetDataGrpc.GetDataImplBase {
        private final FinanceDocumentRepository financeDocumentRepository;

        public FinanceDataImpl(FinanceDocumentRepository financeDocumentRepository) {
            this.financeDocumentRepository = financeDocumentRepository;
        }

        @Override
        public void getData(DataRequest req, StreamObserver<DataResponse> responseObserver) {
            String symbol = req.getRequestData();
            String startDate = req.getStartTime();
            String endDate = req.getEndTime();

            String startId = symbol + "_" + startDate;
            String endId = symbol + "_" + endDate;

            List<DailyStockPriceDocument> dailyPrices = financeDocumentRepository.findDocumentsInRange(startId, endId);

            ArrayList<Date> dateList = extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getDate);
            ArrayList<Double> adjClosePrices = extractDailyDataToArrayList(dailyPrices, DailyStockPriceDocument::getAdjClose);
            List<String> stringPrices = adjClosePrices.stream()
                    .map(d -> Double.toString(d))
                    .toList();

            ListOfString listResponse = ListOfString.newBuilder()
                    .addAllValue(stringPrices)
                    .build();

            DataResponse reply = DataResponse.newBuilder()
                    .setMessage("Get Data.." + req.getRequestData())
                    .setListData(listResponse)
                    .setNotes("This is testing")
                    .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}