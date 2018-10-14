package com.speruri.rest.jpatests;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speruri.rest.model.Country;
import com.speruri.rest.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryJPAServiceTest {

	@Autowired
	CountryService countryService;

	@Test
	public void jpaTestForCountryService() {
		// Create random name and flag
		String countryName = "testCountry" + UUID.randomUUID();
		String flag = "testFlag" + UUID.randomUUID();
		// Create a test record
		Country country = new Country(countryName, flag);
		// Create record
		Country countryResult = countryService.save(country);
		// Get records
		Iterable<Country> countries = countryService.findByNameAndFlag(countryName, flag);
		Assertions.assertThat(countries).first().hasFieldOrPropertyWithValue("name", countryName);
		// Delete the record
		countryService.delete(countryResult);
		Assertions.assertThat(countryService.findByNameAndFlag(countryName, flag)).isEmpty();

	}

}
