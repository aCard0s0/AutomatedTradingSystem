package com.mws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketWatcherService {

    public static void main(String[] args) {
        SpringApplication.run(MarketWatcherService.class, args);
    }

}
