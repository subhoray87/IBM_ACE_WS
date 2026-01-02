package com.ibm.dev;
import java.util.ArrayList;
import java.util.List;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;

public abstract class Countries {

	private static List<Country> countries = new ArrayList<>();

	static {
		countries.add(new Country("United Kingdom", "UK", "London", 67530172));
		countries.add(new Country("France", "FR", "Paris", 65129728));
		countries.add(new Country("Germany", "DE", "Berlin", 83517045));
	}

	public static void getAllCountries(Long limit, MbElement[] output) {
		try {
			MbElement root = output[0];
			long counter = 0;
			for (Country country : countries) {
				if (limit > -1 && counter >= limit) {
					break;
				}
				MbElement item = root.createElementAsLastChild(MbJSON.OBJECT, MbJSON.ARRAY_ITEM_NAME, null);				
				item.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "name", country.getName());
				item.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "abbreviation", country.getAbbreviation());
				item.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "capital", country.getCapital());
				item.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "population", country.getPopulation());
				counter++;
			}
		} catch (MbException e) {
			throw new RuntimeException(e);
		}
	}

	public static MbElement getCountry(String name) throws MbException {
		for (Country country : countries) {
			if (country.getName().equals(name)) {
				MbMessage message = new MbMessage();
				MbElement result = message.getRootElement().createElementAsLastChild(MbJSON.OBJECT, MbJSON.DATA_ELEMENT_NAME, null);
				result.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "name", country.getName());
				result.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "abbreviation", country.getAbbreviation());
				result.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "capital", country.getCapital());
				result.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "population", country.getPopulation());
				return result;
			}
		}
		return null;
	}

	public static MbElement addCountry(String name, String abbreviation, String capital, int population) throws MbException {
		Country country = new Country(name, abbreviation, capital, population);
		countries.add(country);
		MbMessage message = new MbMessage();
		MbElement result = message.getRootElement().createElementAsLastChild(MbJSON.OBJECT, MbJSON.DATA_ELEMENT_NAME, null);
		result.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "message", "A new country with name '" + country.getName() + "' was successfully added to the database.");
		return result;
	}

	public static boolean countryExists(String name) {
		for (Country country : countries) {
			if (country.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	
}
