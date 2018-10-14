package com.speruri.rest.integrationtests;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speruri.rest.controller.CountryController;
import com.speruri.rest.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryIntegrationTest {

	@Autowired
	CountryController countryController;

	@Test
	public void integrationTestForCountryController() {
		// Create random name and flag
		String countryName = "testCountry" + UUID.randomUUID();
		String flag = "testFlag" + UUID.randomUUID();
		// Create a test record
		Country country = new Country(countryName, flag);
		// Create record
		Country countryResult = countryController.create(country);
		// Get records
		Iterable<Country> countries = countryController.search(countryName, flag);
		Assertions.assertThat(countries).first().hasFieldOrPropertyWithValue("name", countryName);
		// Delete the record
		countryController.delete(countryResult.getId());
		Assertions.assertThat(countryController.search(countryName, flag)).isEmpty();
	}

}
