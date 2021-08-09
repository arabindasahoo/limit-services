package com.microservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.model.CurrencyExchage;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchage, Long>{

	CurrencyExchage findByFromAndTo(String from, String to);
}
