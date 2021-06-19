package com.example.demoMongoBulk;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoMongoBulkApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoBulkApplication.class, args);
	}


	@Bean
	MongoClient mongo() {
		return new MongoClient(new MongoClientURI("mongodb://localhost:27017/bulkTest1"));
	}

	@Bean
	MongoTemplate template() {
		return new MongoTemplate(mongo(),"bulkTest1");
	}

}
