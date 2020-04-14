package com.challenge.traceip.repository;

public interface ValueRepository<T> {

	T find(String key);
	
	T save(String ket,T value);
}
