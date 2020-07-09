package com.hcl.ecommerce;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {
	
	private static Logger logger = Logger.getLogger(EcommerceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		logger.info("Welcome to Ecommerce application");
	}


}
