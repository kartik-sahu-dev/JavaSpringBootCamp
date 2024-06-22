package com.example.JsonToDatabase;

import com.example.JsonToDatabase.service.DynamicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonToDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonToDatabaseApplication.class, args);

	}

}
