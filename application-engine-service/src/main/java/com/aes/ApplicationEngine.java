package com.aes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ats.security")
@EntityScan("com.ats.security")
public class ApplicationEngine {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEngine.class, args);
	}

}
