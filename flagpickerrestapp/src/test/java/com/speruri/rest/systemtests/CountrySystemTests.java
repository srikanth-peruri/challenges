package com.speruri.rest.systemtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.speruri.rest.model.Country;

public class CountrySystemTests {

	@Test
	public void systemTestForCountryCreateSearchDelete() {
		try {

			// Create URL
			String createUrl = "http://localhost:8080/country";
			// Delete URL
			String deleteUrl = "http://localhost:8080/country/";
			// Search URL
			String searchUrl = "http://localhost:8080/countries/search?name=";

			// Create REST template to make REST requests
			RestTemplate restTemplate = new RestTemplate();
			assertNotNull(restTemplate);
			// Create random name and flag
			String countryName = "testCountry" + UUID.randomUUID();
			String flag = "testFlag" + UUID.randomUUID();
			// Create a test record
			Country country = new Country(countryName, flag);
			// Create a POST request to insert the test record
			ResponseEntity<Country> postForEntity = restTemplate.postForEntity(createUrl, country, Country.class);
			assertNotNull(postForEntity);
			// Create a GET request to search the records
			Country[] countries = restTemplate.getForObject(searchUrl + countryName, Country[].class);
			Assertions.assertThat(countries).extracting(Country::getName).containsOnly(countryName);

			// Now delete the inserted records
			for (Country c : countries) {
				restTemplate.delete(deleteUrl + c.getId());
			}
			// Now search for the test record and we should see 0 records
			Country[] searchedCountries = restTemplate.getForObject(searchUrl + countryName, Country[].class);
			assertEquals(0, searchedCountries.length);
		} catch (Exception e) {
			System.err.println("Make sure the application is running before running these tests");
		}
	}

}
