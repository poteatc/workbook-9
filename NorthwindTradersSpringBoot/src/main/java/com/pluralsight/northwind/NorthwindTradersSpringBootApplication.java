package com.pluralsight.northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthwindApplication.class, args);
	}


}
