package com.challenge.traceip.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Country;

@Component
public class CountryRepository implements ValueRepository<Country> {

	private final RedisTemplate<String,Country> redisTemplate;
	
	public CountryRepository(RedisTemplate<String,Country> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	public Country find(String code){
		return redisTemplate.opsForValue().get(getEntityKey(code));
	}
	
	public Country save(String key,Country country){
		redisTemplate.opsForValue().set(getEntityKey(key), country);
		return country;
	}
	
	private String getCounterKey(String code) {
		return "counter:" + code;
	}

	private String getEntityKey(String code) {
		return "country:" + code;
	}


}
