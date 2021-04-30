package com.sopa.core.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jaime.springsoap.gen.GetCountryRequest;
import com.jaime.springsoap.gen.GetCountryResponse;
import com.sopa.core.dao.CountriesDAO;

@Endpoint
public class CountriesEndpoint { //el "endpoint" para todos aquellos web services que tengan que ver con el area countries
	private static final String NAMESPACE_URI = "http://www.jaime.com/springsoap/gen"; //esto tiene que coincidir con el target-namespace en su respectivo xsd y en su definicion en WebServicesConfig
	
	private CountriesDAO countriesDAO;

    @Autowired
    public CountriesEndpoint(CountriesDAO countriesDAO) {
        this.countriesDAO = countriesDAO;
    }
   
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest") //el namespace puede ser comun para varios WS, pero la localPart es unica, y debe coincidir con la request del xsd
    @ResponsePayload //esto indica que este WS devuelve un valor en su response
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) { //el requestPayload indica que este WS espera un valor en su request
    	GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countriesDAO.findByName(request.getName())); //el countriesDAO realiza la query que le indiquemos en BBDD y setea el country de respuesta en la response
        return response;
    }
}
