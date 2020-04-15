package com.challenge.traceip.repository;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Currency;

@Component
public class CurrencyRepository extends RedisRepository<Currency> {

	public CurrencyRepository(RedisTemplate<String,Currency> redisTemplate) {
		super(redisTemplate, "currency:");
	}

}
