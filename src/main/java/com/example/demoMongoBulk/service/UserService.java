package com.example.demoMongoBulk.service;

import com.example.demoMongoBulk.modal.User;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {


    List<User> users = Arrays.asList(new User("Bobby","Gurgaon",25),
            new User("Deepak","Gurgaon",25),
            new User("Ronit","Delhi",24));


    @Autowired
    MongoTemplate template;

    private static final String USER_COLECTION="users";


    public void saveAllUserBulkWritesTest() {


        BulkWriteOperation bulkWriteOperation = template.getMongoDbFactory().getLegacyDb()
                .getCollection(USER_COLECTION).initializeUnorderedBulkOperation();
        //Inserting new entity.

        MongoConverter mongoConverter = template.getConverter();

        for (User u : users) {
            BasicDBObject basicDBObject = new BasicDBObject();
            mongoConverter.write(u,basicDBObject);
            //Insert
            bulkWriteOperation.insert(basicDBObject);
        }
        BulkWriteResult writeResult = bulkWriteOperation.execute();
        System.out.println("Inserted record:"+writeResult.getInsertedCount());
        System.out.println("Modified record:"+writeResult.getModifiedCount());
        System.out.println("Macthed record:"+writeResult.getMatchedCount());
        System.out.println("Removed record:"+writeResult.getRemovedCount());


    }



    public void upsertUserBulkWritesTest() {

        //Upsert user
        users = Arrays.asList(new User("Bobby","Gurgaon",25),
                new User("Deepak","Gurgaon",25),
                new User("newUserInsert","Delhi",24));


        BulkWriteOperation bulkWriteOperation = template.getMongoDbFactory().getLegacyDb()
                .getCollection(USER_COLECTION).initializeUnorderedBulkOperation();
        //Inserting new entity.
        MongoConverter mongoConverter = template.getConverter();
        for (User u : users) {
            BasicDBObject query = new BasicDBObject();
            query.put("name",u.getName());

            BasicDBObject basicDBObject = new BasicDBObject();
            u.setAddress("Address updated when reocrd already exist,else insert new record");
            mongoConverter.write(u,basicDBObject);
            //Insert
            bulkWriteOperation.find(query).upsert().replaceOne(basicDBObject);
        }
        BulkWriteResult writeResult = bulkWriteOperation.execute();
        System.out.println("Inserted record:"+writeResult.getInsertedCount());
        System.out.println("Modified record:"+writeResult.getModifiedCount());
        System.out.println("Macthed record:"+writeResult.getMatchedCount());
        System.out.println("Removed record:"+writeResult.getRemovedCount());


    }

    public void replaceBulkWritesTest() {

        //Upsert user
        users = Arrays.asList(new User("Bobby","Gurgaon",25),
                new User("Deepak","Gurgaon",25),
                new User("newUserInsert","Delhi",24),
                new User("chekIfCanbeUpsertWithReplaceOnOnly","Delhi",24));


        BulkWriteOperation bulkWriteOperation = template.getMongoDbFactory().getLegacyDb()
                .getCollection(USER_COLECTION).initializeUnorderedBulkOperation();
        //Inserting new entity.
        MongoConverter mongoConverter = template.getConverter();
        for (User u : users) {
            BasicDBObject query = new BasicDBObject();
            query.put("name",u.getName());

            BasicDBObject basicDBObject = new BasicDBObject();
            u.setAddress("with replace one ,No upsert");
            mongoConverter.write(u,basicDBObject);
            //Insert
            bulkWriteOperation.find(query).replaceOne(basicDBObject);
        }
        BulkWriteResult writeResult = bulkWriteOperation.execute();
        System.out.println("Inserted record:"+writeResult.getInsertedCount());
        System.out.println("Modified record:"+writeResult.getModifiedCount());
        System.out.println("Macthed record:"+writeResult.getMatchedCount());
        System.out.println("Removed record:"+writeResult.getRemovedCount());


    }


}
