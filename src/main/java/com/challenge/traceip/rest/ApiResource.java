package com.challenge.traceip.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.traceip.domain.Country;
import com.challenge.traceip.service.TraceIpService;


/**
 * Controller para api
 * 
 * @author pablo
 *
 */
@RestController
@RequestMapping("/api")
public class ApiResource {

	private final TraceIpService service;
	
    public ApiResource(TraceIpService infoService) {
		super();
		this.service = infoService;
	}

	@PutMapping("/ip/{ip}")
    public Country getIpInfo(@PathVariable String ip) {
		return service.traceIp(ip);
    }

}
