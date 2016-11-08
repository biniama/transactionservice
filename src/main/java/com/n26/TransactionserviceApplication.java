package com.n26;

import com.n26.model.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransactionserviceApplication {

	public static List<Transaction> transactions = new ArrayList<>();

	public static void main(String[] args) {

		// Stating point of Spring Boot Applications
		SpringApplication.run(TransactionserviceApplication.class, args);
	}
}
