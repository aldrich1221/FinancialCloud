package com.finance.dailystockdatamicroservice.repositories;

import com.finance.dailystockdatamicroservice.models.mongoDB.DailyStockPriceDocument;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.List;

public class FinanceDocumentRepository {

    MongoClient mongoClient = MongoClients.create("mongodb://34.81.200.80:27017");
    MongoDatabase database = mongoClient.getDatabase("test_db");
    private MongoTemplate mongoTemplate=new MongoTemplate(mongoClient , "test_db");

    public Set<String> getCollectionSchema(String collectionName) {
        Set<String> fields = new HashSet<>();

        // Fetch a few documents from the collection
        Query query = new Query().limit(100); // Limit the number of documents to analyze
        List<Document> documents = mongoTemplate.find(query, Document.class, collectionName);

        // Iterate through documents and collect field names
        for (Document doc : documents) {
            for (Map.Entry<String, Object> entry : doc.entrySet()) {
                fields.add(entry.getKey());
            }
        }

        return fields;
    }

    public void saveDocument(DailyStockPriceDocument document, String collectionName) {
        mongoTemplate.save(document, collectionName);
    }

    public List<DailyStockPriceDocument> findAllDocuments(String collectionName) {

        return mongoTemplate.findAll(DailyStockPriceDocument.class, collectionName);
    }

    public DailyStockPriceDocument findDocumentById(String id, String collectionName) {
        return mongoTemplate.findById(id, DailyStockPriceDocument.class, collectionName);
    }
    public List<DailyStockPriceDocument> findDocumentsInRange(String startId, String endId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gte(startId).lte(endId));

        return mongoTemplate.find(query, DailyStockPriceDocument.class);
    }

    public void deleteDocumentById(String id, String collectionName) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), DailyStockPriceDocument.class, collectionName);
    }
}
