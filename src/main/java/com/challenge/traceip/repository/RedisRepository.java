package com.challenge.traceip.repository;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Clase base para repositorios basados en Redis.
 * Podria utilizarse spring-data pero para los fines del ejercicio a los fines del ejercicio se prefiere manejarlo de forma explicita.
 * 
 * @author pablo
 *
 * @param <T>
 */
public class RedisRepository<T> implements CacheStore<T> {

	private final RedisTemplate<String,T> redisTemplate;
	private final String prefix;

	public RedisRepository(RedisTemplate<String, T> redisTemplate, String prefix) {
		super();
		this.redisTemplate = redisTemplate;
		this.prefix = prefix;
	}

	public T find(String code){
		return redisTemplate.opsForValue().get(getValueKey(code));
	}
	
	public T save(String key,T value){
		redisTemplate.opsForValue().set(getValueKey(key), value);
		return value;
	}

	private String getValueKey(String code) {
		return prefix + code;
	}

}
