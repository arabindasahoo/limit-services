package com.microservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.model.Currencyconversion;

@FeignClient(name="currency-exchange-service",url="http://localhost:3000")
public interface CurrencyExchangeProxy {

	
	@GetMapping("/exchange/from/{from}/to/{to}")
	public Currencyconversion getvalue(
					@PathVariable String from,
					@PathVariable String to);
}
