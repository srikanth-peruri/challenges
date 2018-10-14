package com.speruri.rest.listners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.speruri.rest.model.Continent;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

public class ContinentEntityListner {

	@PostPersist
	public void handlePostCreate(Continent argContinent) {
		Counter counter = Metrics.counter("continent.insert.count", "type", "continent");
		counter.increment();
	}

	@PostLoad
	public void handlePostLoad(Continent argContinent) {
		Counter counter = Metrics.counter("continent.search.count", "type", "continent");
		counter.increment();
	}

	@PostUpdate
	public void handlePostUpdate(Continent argContinent) {
		Counter counter = Metrics.counter("continent.update.count", "type", "continent");
		counter.increment();
	}

	@PostRemove
	public void handlePostDelete(Continent argContinent) {
		Counter counter = Metrics.counter("continent.delete.count", "type", "continent");
		counter.increment();
	}

}
