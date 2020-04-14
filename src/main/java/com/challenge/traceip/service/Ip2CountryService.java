package com.challenge.traceip.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class Ip2CountryService {

	private @Value("${ip2country.uri:https://api.ip2country.info/ip}") String uri;
	
	private final RestTemplate restTemplate;
	
	public Ip2CountryService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	static class Result {
		String countryCode; 
	}
	
	public String getCountryCode(String ip){
		return restTemplate.getForObject(
				uri + "?" + ip, Result.class).countryCode;
	}
	
}
