package com.challenge.traceip.rest;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.traceip.domain.Country;

/**
 * Controller para api
 * 
 * @author pablo
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminResource {

	private final RedisTemplate<String, Country> redisTemplate;

	public AdminResource(RedisTemplate<String, Country> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	@PostMapping("/clear-all")
	public void clearAll() {
		final RedisCallback<Object> action = new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}

		};
		redisTemplate.execute(action);
	}

}
