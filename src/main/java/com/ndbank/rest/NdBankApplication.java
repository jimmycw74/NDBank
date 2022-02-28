package com.ndbank.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class NdBankApplication {

	public static void main(String[] args) {

		SpringApplication.run(NdBankApplication.class, args);

	}

}
