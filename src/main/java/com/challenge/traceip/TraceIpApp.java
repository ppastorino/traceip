package com.challenge.traceip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.domain.Currency;

@SpringBootApplication()
public class TraceIpApp {

	public static void main(String[] args) {
		SpringApplication.run(TraceIpApp.class, args);
	}
	
	/**
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	**/

	/**
	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			System.out.println("Hola Mundo");
		};
	}
	*/
	
	

}