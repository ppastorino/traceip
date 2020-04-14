package com.challenge.traceip.repository;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Currency;

@Component
public class CurrencyRepository implements ValueRepository<Currency> {

	private final RedisTemplate<String,Currency> redisTemplate;
	
	public CurrencyRepository(RedisTemplate<String,Currency> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	public Currency find(String code){
		return redisTemplate.opsForValue().get(getEntityKey(code));
	}
	
	public Currency save(String key,Currency country){
		redisTemplate.opsForValue().set(getEntityKey(key), country);
		return country;
	}
	
	private String getEntityKey(String code) {
		return "currency:" + code;
	}


}
