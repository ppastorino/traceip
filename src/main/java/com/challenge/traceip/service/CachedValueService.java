package com.challenge.traceip.service;

import com.challenge.traceip.repository.CacheStore;

/**
 * Wrapper de un servicio de consulta de informacion que memoriza el valor en un repositorio. 
 * 
 * @author pablo
 *
 * @param <T>
 */
public class CachedValueService<T> {

	private final CacheStore<T> repository;
	
	private final ValueService<T> service;
	
	public CachedValueService(CacheStore<T> repository, ValueService<T> service) {
		super();
		this.repository = repository;
		this.service = service;
	}

	public T getValue(String key){
		T value = this.repository.find(key);
		if(value != null){
			return value;
		}
		value = service.getValue(key);
		this.repository.save(key, value);
		return value;
	}

}
