package com.speruri.rest.integrationtests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speruri.rest.controller.ContinentController;
import com.speruri.rest.controller.CountryController;
import com.speruri.rest.model.Continent;
import com.speruri.rest.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentIntegrationTest {

	@Autowired
	ContinentController continentController;
	@Autowired
	CountryController countryController;

	@Test
	public void integrationTestForCountryController() {
		// Create random name and flag
		String continentName = "testContinent" + UUID.randomUUID();
		List<Country> cList = new ArrayList<>();
		// Create a test record
		Continent continent = new Continent(continentName, cList);
		// Create record
		Continent countryResult = continentController.create(continent);
		// Get records
		Iterable<Continent> countries = continentController.search(continentName, "", "");
		Assertions.assertThat(countries).first().hasFieldOrPropertyWithValue("continent", continentName);
		// Delete the record
		continentController.delete(countryResult.getId());
		Assertions.assertThat(continentController.search(continentName, "", "")).isEmpty();
	}

}
