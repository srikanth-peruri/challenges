package com.speruri.rest.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speruri.rest.model.Continent;

public interface ContinentService extends JpaRepository<Continent, Integer> {

	Iterable<Continent> findContinentByContinent(String continent);

	Iterable<Continent> findContinentByCountries(String country);

}
