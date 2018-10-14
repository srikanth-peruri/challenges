package com.speruri.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speruri.rest.controller.ContinentController;
import com.speruri.rest.controller.CountryController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagPickerRestApplicationTests {

	@Autowired
	private CountryController countryController;

	@Autowired
	private ContinentController continentController;

	@Test
	public void contextLoads() {
		assertNotNull(countryController);
		assertNotNull(continentController);
	}

}
