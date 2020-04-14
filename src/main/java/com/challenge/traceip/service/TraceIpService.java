package com.challenge.traceip.service;

import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.repository.ValueRepository;

@Component
public class TraceIpService {

	private final Ip2CountryService ip2CountryService;
	
	private PersistentValueService<Country> countryInfoService;

	public TraceIpService(Ip2CountryService ip2CountryService,
			ValueRepository<Country> repository,ValueService<Country> countryInfoService)
	{
		super();
		this.ip2CountryService = ip2CountryService;
		this.countryInfoService = new PersistentValueService<>(repository, countryInfoService);
	}

	public Country traceIp(String ip) {
		final String countryCode = this.ip2CountryService.getCountryCode(ip);
		Country country = countryInfoService.getValue(countryCode); 
		return country;
	}

}
