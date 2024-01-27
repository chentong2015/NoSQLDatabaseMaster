package com.mongodb.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DocumentPojoMapping {

    private static String localUrl = "mongodb://localhost:27017";

    // 将查询的Document结果映射到指定的Java对象
    public static void main(String[] args) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        try (MongoClient mongoClient = MongoClients.create(localUrl)) {
            MongoDatabase database = mongoClient.getDatabase("testdb").withCodecRegistry(pojoCodecRegistry);

            MongoCollection<User> collection = database.getCollection("users", User.class);
            Bson filter = Filters.eq("firstname", "chen");
            User user = collection.find(filter).first();
            System.out.println(user);
        }
    }
}
