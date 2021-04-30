package com.sopa.core.transformers;

import org.springframework.stereotype.Component;

import com.jaime.springsoap.gen.Country;
import com.jaime.springsoap.gen.Currency;
import com.sopa.core.entities.CountryEntity;

@Component
public class CountryTransformer { //para convertir de Country JPA a Country JAXB y viceversa si es necesario
	
	public Country transformOutput(CountryEntity country) {
		Country resCountry=new Country();
    	resCountry.setName(country.getName());
    	resCountry.setPopulation(country.getPopulation());
    	resCountry.setCapital(country.getCapital());
    	resCountry.setCurrency(Currency.fromValue(country.getCurrency()));
        return resCountry;
	}
}
