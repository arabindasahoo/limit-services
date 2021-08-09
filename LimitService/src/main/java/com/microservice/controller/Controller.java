package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.config.Limitconfig;
import com.microservice.model.Limit;

@RestController
public class Controller {
	
	@Autowired
	private Limitconfig limit;

	@GetMapping("/limits")
	public Limit getLimit()
	{
		return new Limit(limit.getMaximum(),limit.getMinimum());
	}
}
