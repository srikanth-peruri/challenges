package com.speruri.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlagPickerRestApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(FlagPickerRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlagPickerRestApplication.class, args);
		LOGGER.debug("Application Started");
	}

}
