package com.sopa.core.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServicesConfig  extends WsConfigurerAdapter { //esta clase sirve para generar los WSDL y exponerlos, tambien define el Servlet que escucha las peticiones

	//SERVLET, preferiblemente comun para todos los ws
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) { //define el Servlet
	    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	    servlet.setApplicationContext(applicationContext);
	    servlet.setTransformWsdlLocations(true);
	    return new ServletRegistrationBean(servlet, "/ws/*"); //el servlet escuchara las peticiones a todos los Web Services cuyo WSDL este en esta ruta (ws)
	}
	
	//DEFINICION DE WSDLS
	//cada bean representa un Web Service, con su WSDL y su XSD unicos
	
	@Bean(name = "countries") //el nombre del bean sera el nombre del wsdl que se genere
	public DefaultWsdl11Definition defaultWsdl11Definition() { //a partir de un xsd, genera y expone un WSDL
	    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	    wsdl11Definition.setPortTypeName("CountriesPort"); //nombre del puerto, sin mayor importancia, podemos poner lo que queramos
	    wsdl11Definition.setLocationUri("/ws"); //la ruta web donde se va a generar el WSDL
	    wsdl11Definition.setTargetNamespace("http://www.jaime.com/springsoap/gen"); //el target-namespace, importante que coincida con el del xsd y el del endpoint
	    wsdl11Definition.setSchema(xsdSchema("contrato.xsd")); //el xsd utilizado para generar el WSDL
	    return wsdl11Definition;
	}
	
	//METODO PARA ESQUEMA XSD PARAMETRIZADO
	@Bean
	public XsdSchema xsdSchema(String contrato) {
		return new SimpleXsdSchema(new ClassPathResource(contrato));
	}
	
	
}
