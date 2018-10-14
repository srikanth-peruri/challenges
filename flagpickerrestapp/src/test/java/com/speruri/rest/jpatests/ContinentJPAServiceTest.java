package com.speruri.rest.jpatests;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speruri.rest.model.Continent;
import com.speruri.rest.service.ContinentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentJPAServiceTest {

	@Autowired
	ContinentService continentService;

	@Test
	public void jpaTestForCountryService() {
		// Create random name
		String continentName = "testCountry" + UUID.randomUUID();
		// Create a test record
		Continent continent = new Continent(continentName, null);
		// Create record
		Continent continentResult = continentService.save(continent);
		// Get records
		Iterable<Continent> countries = continentService.findContinentByContinent(continentName);
		Assertions.assertThat(countries).first().hasFieldOrPropertyWithValue("continent", continentName);
		// Delete the record
		continentService.delete(continentResult);
		Assertions.assertThat(continentService.findContinentByContinent(continentName)).isEmpty();
	}

}
