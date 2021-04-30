package com.sopa.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sopa.core.entities.CountryEntity;


public interface CountriesDAO extends JpaRepository<CountryEntity, Long>, CountriesDAOCustom{ //por un lado hereda metodos CRUD de JPA, y por otro metodos custom que definimos en el impl
	//aqui hariamos override de metodos-queries por defecto de JPA, como findById o findAll
}
