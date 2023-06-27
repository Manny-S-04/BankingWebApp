package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bank.services.CurrAccountService;
import com.bank.services.CurrAccountServiceImpl;
import com.bank.services.SavAccountService;
import com.bank.services.SavAccountServiceImpl;

@SpringBootApplication
@ComponentScan({"com.bank.controllers", "com.bank.services", "com.bank.repositories"})
@EntityScan("com.bank.entities")
@EnableJpaRepositories(basePackages = "com.bank.repositories")
public class FinalBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalBankAppApplication.class, args);
	}
	



}
		