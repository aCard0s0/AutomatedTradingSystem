package com.ats.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ats.security")
@EntityScan("com.ats.security")
public class AutomatedTradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatedTradingSystemApplication.class, args);
	}

}
