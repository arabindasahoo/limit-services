package com.microservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.model.Currencyconversion;

@RestController
public class CurrencyConersionController {

	@Autowired
	Environment env;
	
	@Autowired(required = false)
	private CurrencyExchangeProxy proxy;

	private Logger logger = LoggerFactory.getLogger(CurrencyConersionController.class);
	
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{amount}")
	public Currencyconversion getvalue(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal amount)
	{
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<Currencyconversion> forEntity = restTemplate.getForEntity("http://localhost:3000/exchage/from/{from}/to/{to}", Currencyconversion.class, uriVariables);
		
		Currencyconversion body = forEntity.getBody();
		body.setQuantity(amount);
		
		logger.info(" ",body);
		//body.setEnvironment(env.getProperty("local.server.port"));
		
		
		return new Currencyconversion(
				body.getId(),
				from,to,
				body.getConversionMultiple(),
				amount.multiply(body.getConversionMultiple()),
				body.getEnvironment(),
				amount
				);
		
		//return new Currencyconversion(10L, from, to, BigDecimal.valueOf(650l), BigDecimal.valueOf(100L), BigDecimal.valueOf(10L));
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{amount}")
	public Currencyconversion getValueFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal amount)
	{
		
		
		Currencyconversion getvalue = proxy.getvalue(from, to);
		
		return new Currencyconversion(getvalue.getId(), from, to, getvalue.getConversionMultiple(), 
				amount.multiply(getvalue.getConversionMultiple()), getvalue.getEnvironment()
				, amount);
		
		
		//return new Currencyconversion(10L, from, to, BigDecimal.valueOf(650l), BigDecimal.valueOf(100L), BigDecimal.valueOf(10L));
	}
}
