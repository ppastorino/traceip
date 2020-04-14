package com.challenge.traceip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.domain.Currency;

@Configuration
public class RedisConfiguration {

	@Bean
	JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host:localhost}") String host, @Value("${redis.port:6379}") int port) {
		RedisStandaloneConfiguration stdaloneConfig = new RedisStandaloneConfiguration(host,port);
		return  new JedisConnectionFactory(stdaloneConfig);
	}
	 
	@Bean
	public RedisTemplate<String, Country> redisTemplate4Country(JedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, Country> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}
	
	@Bean
	public RedisTemplate<String, Currency> redisTemplate4Currency(JedisConnectionFactory connectionFactory) {
	    RedisTemplate<String, Currency> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory);
	    return template;
	}

}
