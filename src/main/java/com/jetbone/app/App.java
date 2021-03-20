package com.jetbone.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

/**
 * @author Chris
 */
@SpringBootApplication
public class App {

	public static final Logger logger = Logger.getLogger("App.class");

	public static void main(String[] args) {
		logger.info("start:!!!!!");
		SpringApplication.run(App.class, args);
	}

}
