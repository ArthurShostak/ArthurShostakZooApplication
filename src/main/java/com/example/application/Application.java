package com.example.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.application")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}