package com.finance.dailystockdatamicroservice.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoFinanceRepository {

    public void getMongoStockPriceSchemaBySymbol(String[] symbols){
        MongoClient mongoClient = MongoClients.create("mongodb://34.81.200.80:27017");
        MongoDatabase database = mongoClient.getDatabase("test_db");
        // Create a collection
        for(String symbol:symbols) {


            MongoCollection<org.bson.Document> collection = database.getCollection(symbol);

            // Analyze document structure
            for (org.bson.Document doc : collection.find().limit(10)) {
                System.out.println(doc.toJson());
            }

        }
        mongoClient.close();
    }


}
