package com.challenge.traceip.repository;

/**
 * Interface para stores de cache.
 * 
 * Podria utilizarse la anotacion @Cacheable de Spring pero a los fines del ejercicio se prefiere manejarlo de forma explicita. 
 *  
 * @author pablo
 *
 * @param <T>
 */
public interface CacheStore<T> {

	T find(String key);
	
	T save(String ket,T value);
}
