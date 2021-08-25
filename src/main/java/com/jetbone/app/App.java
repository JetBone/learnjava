package com.jetbone.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Chris
 */
@EnableAsync
@EnableCaching
@SpringBootApplication
public class App {

	public static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("start:!!!!!");
		SpringApplication.run(App.class, args);
	}

}
