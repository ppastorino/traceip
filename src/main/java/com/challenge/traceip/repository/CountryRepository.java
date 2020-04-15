package com.challenge.traceip.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.challenge.traceip.domain.Country;

@Component
public class CountryRepository extends RedisRepository<Country> {

	public CountryRepository(RedisTemplate<String,Country> redisTemplate) {
		super(redisTemplate,"country:");
	}
}
