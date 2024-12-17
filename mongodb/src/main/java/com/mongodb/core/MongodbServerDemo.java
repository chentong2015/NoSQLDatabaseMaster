package com.mongodb.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongodbServerDemo {

    private static String remoteUrl = "mongodb+srv://<username>:<password>@***.mongodb.net/test-db";
    private static String localUrl = "mongodb://localhost:27017"; // 无用户名和密码

    // 从指定的DB中找Collections集合，再过滤找到Document文件(Json数据格式)
    public static void main( String[] args ) {
        try (MongoClient mongoClient = MongoClients.create(localUrl)) {
            MongoDatabase database = mongoClient.getDatabase("testdb");
            MongoCollection<Document> userCollection = database.getCollection("users");

            // 1. Insert Documents
            userCollection.insertOne(Document.parse("{firstname: 'chen2', lastname:'tong2'}"));
            userCollection.insertOne(Document.parse("{firstname: 'chen3', lastname:'tong3', age: 30}"));

            // 2. Update Documents 根据指定的判断条件进行修改
            Bson filter = Filters.eq("firstname", "chen2");
            Bson update = Updates.set("lastname", "tong tong");
            userCollection.updateOne(filter, update);

            // 3. Delete Documents
            userCollection.deleteOne(Filters.eq("firstname", "chen2"));

            // 4. Query Documents
            Document doc = userCollection.find(filter).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}
