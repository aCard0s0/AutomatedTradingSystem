package com.ats.app;

import com.ats.app.service.MarketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ats.security")
@EntityScan("com.ats.security")
public class ApplicationEngine {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEngine.class, args);
	}

}
