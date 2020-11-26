package ru.ttr.mongo_with_java.mongo_driver;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.ttr.mongo_with_java.mongo_driver.config.Configuration;

import java.util.Arrays;

import static com.mongodb.client.model.Projections.*;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration("example");
        MongoDatabase mongoDatabase = configuration.getMongoDatabase();
        //first example(Use getCollection)
        MongoCollection<Document> usersCollections = mongoDatabase.getCollection("users");
        usersCollections.find().forEach(document -> System.out.println(document.get("course","Java")));
        //second example(Use Document,FindIterable with projection)
        Document searchQuery = new Document();
        searchQuery
                .append("course","Java")
                .append("age",20)
                .append("$or", Arrays.asList(
                        new Document("country", "Russia"),
                        new Document("rate",new Document("$lt",86.0))
                ));
        FindIterable<Document> resultDocuments = usersCollections.find(searchQuery)
                .projection(fields(include("firstname", "lastname", "age"), excludeId()));
        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }
    }
}
