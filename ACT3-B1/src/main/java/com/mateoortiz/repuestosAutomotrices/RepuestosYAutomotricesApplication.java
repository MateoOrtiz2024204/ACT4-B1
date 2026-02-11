package com.mateoortiz.repuestosAutomotrices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepuestosYAutomotricesApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RepuestosYAutomotricesApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception{
		System.out.println("Test Api");
	}

}
