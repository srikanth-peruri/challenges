package com.speruri.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.speruri.rest.listners.ContinentEntityListner;

@EntityListeners(ContinentEntityListner.class)
@Entity
public class Continent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String continent;

	@JsonManagedReference
	@OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Country> countries;

	public Continent() {

	}

	public Continent(String continent, List<Country> countries) {
		super();
		this.continent = continent;
		this.countries = countries;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Continent [id=" + id + ", continent=" + continent + ", countries=" + countries + "]";
	}

}
