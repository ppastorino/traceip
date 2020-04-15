package com.challenge.traceip.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.challenge.traceip.domain.Currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class CurrencyService implements ValueService<Currency> {

	private @Value("${fixer.uri:http://data.fixer.io/api/latest}")  String uri;
	private @Value("${fixer.key:1cd197ba67c5ce624d7bdf2b68651a78}") String apiKey;
	
	private final RestTemplate restTemplate;
		
	public CurrencyService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Result {
		String base;
		Map<String,String> rates;
				
		BigDecimal getRate(String code){
			if(rates != null){
				final String v = rates.get(code);
				return v != null ?  BigDecimal.ONE.divide(new BigDecimal(v),3,BigDecimal.ROUND_HALF_DOWN) : null;
			}
			return null;
		}
	}
	
	public Currency getValue(String currencyCode){
		final Result result = restTemplate.getForObject(uri + "?access_key=" + apiKey + "&symbols=" + currencyCode, Result.class);
		return Currency.builder().base(result.base).code(currencyCode).rate(result.getRate(currencyCode)).build();
	}
	
}
