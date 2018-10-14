package com.speruri.rest.listners;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Metrics;

@Endpoint(id = "appmetrics")
@Component
public class FlagPickerEndpoint {

	private Map<String, Object> map = new HashMap<>();

	@Bean
	@ReadOperation
	public Map showMetrics() {
		
		//Continent Metrics
		map.put("continent.insert.count", Metrics.counter("continent.insert.count", "type", "continent").count());
		map.put("continent.search.count", Metrics.counter("continent.search.count", "type", "continent").count());
		map.put("continent.update.count", Metrics.counter("continent.update.count", "type", "continent").count());
		map.put("continent.delete.count", Metrics.counter("continent.delete.count", "type", "continent").count());
		
		//Country Metrics
		map.put("country.insert.count", Metrics.counter("country.insert.count", "type", "country").count());
		map.put("country.search.count", Metrics.counter("country.search.count", "type", "country").count());
		map.put("country.update.count", Metrics.counter("country.update.count", "type", "country").count());
		map.put("country.delete.count", Metrics.counter("country.delete.count", "type", "country").count());

		
		return map;
	}

}
