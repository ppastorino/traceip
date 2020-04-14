package com.challenge.traceip.service;

import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Currency;

@Component
public class CurrencyService implements ValueService<Currency> {

	public Currency getValue(String currencyCode){
		return new Currency();
	}
	
}
