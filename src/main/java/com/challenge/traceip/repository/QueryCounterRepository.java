package com.challenge.traceip.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

/**
 * Repositorio para almacenar/ consultar los contadores de consultas por pais.
 * 
 * @author pablo
 *
 */
@Component
public class QueryCounterRepository  {

	private static final String PREFIX = "counter:";
	private final RedisTemplate<String,String> redisTemplate;
	
	public QueryCounterRepository(RedisTemplate<String,String> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	public long increment(String code){
		return redisTemplate.opsForValue().increment(this.getEntryKey(code));
	}

	private String getEntryKey(String code) {
		return PREFIX + code;
	}

	public Map<String,Long> findAll(){
		
		final ScanOptions options = ScanOptions.scanOptions().match(PREFIX + "*").build();
		
		RedisCallback<HashMap<String,Long>> action = new RedisCallback<HashMap<String,Long>>() {
			
			@Override
			public HashMap<String,Long> doInRedis(RedisConnection connection) throws DataAccessException {
				final HashMap<String,Long> result=new HashMap<>();

				try(Cursor<byte[]> cursor = connection.scan(options)){
					while(cursor.hasNext()){
						final byte[] k = cursor.next();
						final String code = new String(k);
						final Long value = Long.parseLong(new String(connection.get(k)));
						result.put(code.substring(PREFIX.length()), value);
					}
				} catch (IOException unused) {
					// TODO Auto-generated catch block
				}
				return result;
			}
		};
		return redisTemplate.execute(action);
	}
	
	
}
