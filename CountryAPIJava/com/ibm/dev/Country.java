package com.ibm.dev;


public class Country {

	private String name;
	private String abbreviation;
	private String capital;
	private int population;

	public Country(String name, String abbreviation, String capital, int population) {

		this.name = name;
		this.abbreviation = abbreviation;
		this.capital = capital;
		this.population = population;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	
	
}
