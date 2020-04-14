package com.challenge.traceip.service;

import com.challenge.traceip.repository.ValueRepository;

public class PersistentValueService<T> {

	private final ValueRepository<T> repository;
	
	private final ValueService<T> service;
	
	public PersistentValueService(ValueRepository<T> repository, ValueService<T> service) {
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
