package com.speruri.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speruri.rest.BootupTasks;
import com.speruri.rest.model.Continent;
import com.speruri.rest.service.ContinentService;

@RestController
public class ContinentController {

	private static Logger LOGGER = LoggerFactory.getLogger(ContinentController.class);

	@Autowired
	private ContinentService continentService;

	// Create
	@PostMapping("/continent")
	public Continent create(@Valid @RequestBody Continent argContinent) {
		LOGGER.debug("Creating the continent record");
		return this.continentService.save(argContinent);
	}

	// Create multiple Continents
	@PostMapping("/continents")
	public Iterable<Continent> createContinents(@Valid @RequestBody List<Continent> continents) {
		LOGGER.debug("Creating multiple continent records");
		return this.continentService.saveAll(continents);
	}

	// Retrieve
	@GetMapping("/continents")
	public List<Continent> read() {
		LOGGER.debug("Fetching all the continent records");
		List<Continent> continents = new ArrayList<>();
		this.continentService.findAll().forEach(continent -> continents.add(continent));
		return continents;
	}

	// Update
	@PutMapping("/continent")
	public Continent update(@Valid @RequestBody Continent argContinent) {
		if (argContinent != null && argContinent.getContinent() != null && argContinent.getId() != null
				&& argContinent.getId() != 0) {
			return this.continentService.save(argContinent);
		}
		throw new ValidationException("Continent cannot be updated");
	}

	// Delete
	@DeleteMapping("/continent/{id}")
	public void delete(@Valid @PathVariable Integer id) {
		try {
			LOGGER.debug("Deleting the continent record with ID : {}", id);
			this.continentService.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("Cannot delete the continent : " + e);
			throw new ValidationException("Cannot delete the continent");
		}
	}

	// Search
	@GetMapping("/continents/search")
	public Iterable<Continent> search(@RequestParam(value = "name", required = false) String continent,
			@RequestParam(value = "flag", required = false) String flag,
			@RequestParam(value = "country", required = false) String country) {
		if (country != null && !country.isEmpty()) {
			LOGGER.debug("finding the continent by country");
			return this.continentService.findContinentByCountries(country);
		} else if (continent != null && !continent.isEmpty()) {
			LOGGER.debug("finding the continent by continent");
			return this.continentService.findContinentByContinent(continent);
		}
		LOGGER.debug("Returning all the continents since there is no parameters found");
		return this.read();
	}
}
