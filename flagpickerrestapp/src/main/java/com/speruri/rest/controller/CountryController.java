package com.speruri.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.speruri.rest.model.Country;
import com.speruri.rest.service.CountryService;

@RestController
public class CountryController {

	private static Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	// Create
	@PostMapping("/country")
	public Country create(@Valid @RequestBody Country argCountry) {
		LOGGER.debug("Saving the Country");
		return this.countryService.save(argCountry);
	}

	// Retrieve
	@GetMapping("/countries")
	public Iterable<Country> read() {
		List<Country> countries = new ArrayList<>();
		this.countryService.findAll().forEach(countries::add);
		LOGGER.debug("Returning the list of Countries");
		return countries;
	}

	// Update
	@PutMapping("/country")
	public ResponseEntity<Country> update(@Valid @RequestBody Country argCountry) {
		if (!this.countryService.findById(argCountry.getId()).isPresent()) {
			LOGGER.error("Cannot update the Country");
			throw new ValidationException("Cannot update the Country");
		}
		LOGGER.debug("Updating the Country");
		return new ResponseEntity(this.countryService.save(argCountry), HttpStatus.OK);
	}

	// Delete
	@DeleteMapping("/country/{id}")
	public void delete(@Valid @PathVariable Integer id) {
		try {
			LOGGER.debug("Deleting the Country with ID : {}", id);
			this.countryService.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException("Cannot delete the Country");
		}
	}

	// Search
	@GetMapping("/countries/search")
	public Iterable<Country> search(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "flag", required = false) String flag) {
		LOGGER.debug("Search parameters we got is name : {}, flag : {}", name, flag);
		// If both country and flag are not null, then search for both
		if (name != null && !name.isEmpty() && flag != null && !flag.isEmpty()) {
			return this.countryService.findByNameAndFlag(name, flag);
		} else if (name != null && !name.isEmpty()) {
			// If both country is not null, then search with country
			return this.countryService.findByName(name);
		} else if (flag != null && !flag.isEmpty()) {
			// If both flag is not null, then search with flag
			return this.countryService.findByFlag(flag);
		}
		// else find all
		return this.read();
	}
}
