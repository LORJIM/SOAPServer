package com.sopa.core.dao;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopa.core.entities.CountryEntity;
import com.sopa.core.transformers.CountryTransformer;
import com.jaime.springsoap.gen.Country;


@Repository //identificar esta clase como un bean
public class CountriesDAOImpl implements CountriesDAOCustom { //en esta clase se definen los metodos o queries complejas-custom
	@Autowired
    private EntityManager entityManager;
	@Autowired
    private CountryTransformer countryTransformer;
	
	@Override
	@Transactional //prescindir de begin transaction, commit y rollback
	public Country findByName(String name) {
		//obtener session
    	Session session=entityManager.unwrap(Session.class);
    			
    	//Obtener el usuario en BBDD por su username
    	//Crear la consulta (query)
    	Query<CountryEntity> miQuery=session.createQuery("from CountryEntity where name=:nombrePais", CountryEntity.class);
    	miQuery.setParameter("nombrePais", name); 
    	CountryEntity country=miQuery.uniqueResult(); //execute query, obtenemos una clase JPA
    	return countryTransformer.transformOutput(country); //transformamos la clase JPA en clase JAXB para poder enviarla en la response del WS
    	
	}

}
