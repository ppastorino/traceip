package com.challenge.traceip.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.domain.Currency;
import com.challenge.traceip.domain.IpInfo;
import com.challenge.traceip.domain.Stats;
import com.challenge.traceip.repository.CacheStore;
import com.challenge.traceip.repository.QueryCounterRepository;

/**
 * Service Facade
 * 
 * @author pablo
 *
 */
@Component
public class TraceIpService {

	private final Ip2CountryService ip2CountryService;
	
	private CachedValueService<Country> countryService;
	private CachedValueService<Currency> currencyService;

	private QueryCounterRepository counterRepository;

	public TraceIpService(Ip2CountryService ip2CountryService,
			CacheStore<Country>  countryRepository,ValueService<Country> countryService,
			CacheStore<Currency> currencyRepository,ValueService<Currency> currencyService,
			QueryCounterRepository counterRepository)
	{
		super();
		this.ip2CountryService = ip2CountryService;
		this.countryService = new CachedValueService<>(countryRepository, countryService);
		this.currencyService = new CachedValueService<>(currencyRepository, currencyService);
		this.counterRepository = counterRepository;
	}

	public IpInfo traceIp(String ip) {
		final String countryCode = this.ip2CountryService.getCountryCode(ip);
		final Country country = countryService.getValue(countryCode); 
		final Currency currency = currencyService.getValue(country.getCurrencyCode());
		counterRepository.increment(countryCode);
		return IpInfo.builder().country(country).currency(currency).build();
	}

	public Map<String,Long> getCounters() {
		return counterRepository.findAll();
	}

	public Stats getStats(){
		final Stats stats = new Stats();
		final Map<String,Long> counters = this.getCounters();
	
		for(Map.Entry<String, Long> e: counters.entrySet()){
			final Country country = this.countryService.getValue(e.getKey());
			stats.update(e.getKey(), country.getDistance(), e.getValue());
		}
		return stats;
	}
	
}
