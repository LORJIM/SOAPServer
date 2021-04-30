package com.sopa.core.dao;

import com.jaime.springsoap.gen.Country;

public interface CountriesDAOCustom {
	public Country findByName(String name);
}
