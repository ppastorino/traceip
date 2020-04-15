package com.challenge.traceip.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.traceip.domain.IpInfo;
import com.challenge.traceip.domain.Stats;
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

	@GetMapping("/trace")
    public IpInfo getIpInfo(@RequestParam String ip) {
		return service.traceIp(ip);
    }

	@GetMapping("/stats")
    public Stats getStats() {
		return service.getStats();
    }
	
	@GetMapping("/counters")
    public Map<String, Long> getCounters() {
		return service.getCounters();
    }

}
