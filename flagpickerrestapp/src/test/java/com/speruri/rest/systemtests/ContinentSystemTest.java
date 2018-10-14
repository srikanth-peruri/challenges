package com.speruri.rest.systemtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.speruri.rest.model.Continent;
import com.speruri.rest.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContinentSystemTest {

	/*
	 * IMPORTANT : THIS IS A SYSTEM TEST. MAKE SURE YOU ARE RUNNING THE APPLICATION
	 * BEFORE YOU ARE RUNNING THIS TESTS. THIS IS LIKE A POSTMAN MAKING THE REST
	 * CALLS TO THE REST ENDPOINT AND THEN VALIDATES THE RESPONSE.
	 */

	@Test
	public void systemTestForContinentCreateSearchDelete() {
		try {

			// Create URL
			String createUrl = "http://localhost:8080/continent";
			// Delete URL
			String deleteUrl = "http://localhost:8080/continent/";
			// Search URL
			String searchUrl = "http://localhost:8080/continents/search?name=";

			// Create REST template to make REST requests
			RestTemplate restTemplate = new RestTemplate();
			assertNotNull(restTemplate);
			// Create random name and flag
			String countryName = "testCountry" + UUID.randomUUID();
			String flag = "testFlag" + UUID.randomUUID();
			// Create a test record
			Country country = new Country(countryName, flag);

			String continentName = "testContinent" + UUID.randomUUID();
			List<Country> cList = new ArrayList<>();
			cList.add(country);

			Continent continent = new Continent(continentName, cList);

			// Create a POST request to insert the test record
			ResponseEntity<Continent> postForEntity = restTemplate.postForEntity(createUrl, continent, Continent.class);
			assertNotNull(postForEntity);
			// Create a GET request to search the records
			Continent[] continents = restTemplate.getForObject(searchUrl + continentName, Continent[].class);
			Assertions.assertThat(continents).extracting(Continent::getContinent).containsOnly(continentName);

			// Now delete the inserted records
			for (Continent c : continents) {
				restTemplate.delete(deleteUrl + c.getId());
			}
			// Now search for the test record and we should see 0 records
			Continent[] searchedContinents = restTemplate.getForObject(searchUrl + continentName, Continent[].class);
			assertEquals(0, searchedContinents.length);
		} catch (Exception e) {
			System.err.println("Make sure the application is running before running these tests");
		}
	}

}
