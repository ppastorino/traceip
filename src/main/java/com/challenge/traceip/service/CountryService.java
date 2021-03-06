package com.challenge.traceip.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.domain.Language;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class CountryService implements ValueService<Country>{

	private @Value("${restcountries.uri:https://restcountries.eu/rest/v2/alpha}") String uri;

	private @Value("${location.latitude:-34.603683}") double currentLatitude;
	
	private @Value("${location.longitude:-58.381557}") double currentLongitude;
	
	private final RestTemplate restTemplate;

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Result {
		String name;
		String[] timezones;
		CurrencyDTO[] currencies;
		LanguageDTO[] languages;
		double[] latlng;
		
		@Getter
		@Setter
		@NoArgsConstructor
		public static class CurrencyDTO {
			String code;
		}

		@Getter
		@Setter
		@NoArgsConstructor
		public static class LanguageDTO {
			String iso639_1;
			String name;
		}

		public String getDefaultCurrencyCode() {
			return currencies != null && currencies.length > 0 ? currencies[0].code : null;
		}
	}

	public CountryService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	public Country getValue(String countryCode) {
		final Result result = restTemplate.getForObject(uri + "/" + countryCode, Result.class);
		
		final Language[] languages = Arrays.stream(result.languages)
				.map(l -> Language.builder().code(l.iso639_1).name(l.name).build()).toArray(n -> new Language[n]);
		
		final double distance = DistanceCalculator.distance(this.currentLatitude, this.currentLongitude, 
				result.latlng[0], result.latlng[1]);
		
		return Country.builder().code(countryCode).timeZones(result.timezones)
				.currencyCode(result.getDefaultCurrencyCode())
				.languages(languages)
				.distance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_DOWN))
				.build();

	}

}
