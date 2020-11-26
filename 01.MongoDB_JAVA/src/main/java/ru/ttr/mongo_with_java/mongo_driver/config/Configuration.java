package ru.ttr.mongo_with_java.mongo_driver.config;

import com.mongodb.client.*;

public class Configuration {
    private final MongoDatabase mongoDatabase;

    public Configuration(String database) {
        MongoClient mongoClient = MongoClients.create();
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
