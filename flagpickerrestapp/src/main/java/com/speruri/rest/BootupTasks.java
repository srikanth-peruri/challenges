package com.speruri.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speruri.rest.model.Continent;
import com.speruri.rest.service.ContinentService;

@Component
public class BootupTasks {

	private static Logger LOGGER = LoggerFactory.getLogger(BootupTasks.class);

	@Autowired
	private ContinentService continentService;

	@PostConstruct
	public void init() {
		// Check for the existing default records in DB in case of persistent DB
		Iterable<Continent> continentsInDB = this.continentService.findAll();
		if (continentsInDB != null) {
			if (continentsInDB.iterator().hasNext()) {
				return;
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
		};
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getResourceAsStream("/continents.json");
			List<Continent> continents = mapper.readValue(inputStream, typeReference);
			this.continentService.saveAll(continents);
			LOGGER.debug("Saved continent to the DB");
		} catch (IOException e) {
			LOGGER.debug("Unable to save users: " + e.getMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception ignore) {
				// Ignore
			}
		}
	}

}
