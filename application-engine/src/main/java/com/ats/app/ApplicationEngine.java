package com.ats.app;

import com.ats.app.domain.Candle;
import com.ats.app.service.CandleService;
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
public class ApplicationEngine implements CommandLineRunner {

	public ApplicationEngine(CandleService candleService) {
		this.candleService = candleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationEngine.class, args);
	}

	private final CandleService candleService;

	@Override
	public void run(String... args) {
		List<Candle> users = Arrays.asList(
				new Candle("Alice"),
				new Candle("Bob"),
				new Candle("Charlie")
		);
		candleService.saveAll(users);
	}
}
