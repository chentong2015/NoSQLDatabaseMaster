package com.mongodb.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DemoMongoDB {

    // MongoDB: 连接数据库，从指定的DB中找Collections集合，再过滤找到Document文件
    // 远程DB Server连接: mongodb+srv://<username>:<password>@***.mongodb.net/test-db
    // 本地DB Server连接: mongodb://localhost:27017/<database>
    public static void main( String[] args ) {
        String uri = "<connection string uri>";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_db");
            MongoCollection<Document> collection = database.getCollection("movies");

            Bson filter = Filters.eq("title", "007");
            Document doc = collection.find(filter).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    // 将查询的Document结果映射到指定的Java对象
    public void mapDocumentToPOJO() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        String uri = "<connection string uri>";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_db").withCodecRegistry(pojoCodecRegistry);

            MongoCollection<Movie> collection = database.getCollection("movies", Movie.class);
            Bson filter = Filters.eq("title", "007");
            Movie movie = collection.find(filter).first();
            System.out.println(movie);
        }
    }

    public class Movie {
        String plot;
        String title;
    }
}
