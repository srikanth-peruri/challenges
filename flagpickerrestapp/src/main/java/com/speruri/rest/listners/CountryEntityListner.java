package com.speruri.rest.listners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.speruri.rest.model.Country;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

public class CountryEntityListner {

	@PostPersist
	public void handlePostCreate(Country argCountry) {
		Counter counter = Metrics.counter("country.insert.count", "type", "country");
		counter.increment();
	}

	@PostLoad
	public void handlePostLoad(Country argCountry) {
		Counter counter = Metrics.counter("country.search.count", "type", "country");
		counter.increment();
	}

	@PostUpdate
	public void handlePostUpdate(Country argCountry) {
		Counter counter = Metrics.counter("country.update.count", "type", "country");
		counter.increment();
	}

	@PostRemove
	public void handlePostDelete(Country argCountry) {
		Counter counter = Metrics.counter("country.delete.count", "type", "country");
		counter.increment();
	}

}
