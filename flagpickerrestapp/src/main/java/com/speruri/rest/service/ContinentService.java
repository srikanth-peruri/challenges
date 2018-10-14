package com.speruri.rest.service;

import org.springframework.data.repository.CrudRepository;

import com.speruri.rest.model.Continent;

public interface ContinentService extends CrudRepository<Continent, Integer> {

	Iterable<Continent> findContinentByContinent(String continent);

	Iterable<Continent> findContinentByCountries(String country);

}
