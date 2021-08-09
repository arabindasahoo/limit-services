package com.microservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.CurrencyExchage;
import com.microservice.repo.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository CurrenyRepo;
	
	@GetMapping("/exchage/from/{from}/to/{to}")
	public CurrencyExchage getExchage(@PathVariable("from") String from,@PathVariable String to)
	{
		//CurrencyExchage currencyExchage = new CurrencyExchage(100L,"USD","INR",BigDecimal.valueOf(50L));
		CurrencyExchage currencyExchage = CurrenyRepo.findByFromAndTo(from, to);
		if(currencyExchage == null)
		{
			throw new RuntimeException("Unable to find data");
		}
		
		currencyExchage.setEnvironment(environment.getProperty("local.server.port"));
		return currencyExchage ;
		
		
	}
}
