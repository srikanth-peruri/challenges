package com.speruri.rest.service;

import org.springframework.data.repository.CrudRepository;

import com.speruri.rest.model.Country;

public interface CountryService extends CrudRepository<Country, Integer> {

	Iterable<Country> findByNameAndFlag(String name, String flag);

	Iterable<Country> findByName(String name);

	Iterable<Country> findByFlag(String flag);

}
