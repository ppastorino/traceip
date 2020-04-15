package com.challenge.traceip.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Estadisticas 
 * 
 * @author pablo
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Stats {

	public void update(String country, BigDecimal distance, long queryCount){
		updateMax(country, distance);
		updateMin(country, distance);
		sumQuery+=queryCount;
		sumDistance=sumDistance.add(distance.multiply(new BigDecimal(queryCount)));
	}
	
	private void updateMax(String country, BigDecimal distance){
		if(maxDistance == null || maxDistance.compareTo(distance) < 0){
			maxDistance = distance;
			maxDistanceCountry = country;
		}
	}

	private void updateMin(String country, BigDecimal distance){
		if(minDistance == null || minDistance.compareTo(distance) > 0){
			minDistance = distance;
			minDistanceCountry = country;
		}
	}

	private BigDecimal maxDistance;
	private String maxDistanceCountry;
	
	private BigDecimal minDistance;
	private String minDistanceCountry;
	
	@JsonIgnore
	private BigDecimal sumDistance = BigDecimal.ZERO;
	
	@JsonIgnore
	private long sumQuery;
	
	public BigDecimal getAvg(){
		return this.sumQuery > 0 ? 
				sumDistance.divide(new BigDecimal(this.sumQuery), 3, BigDecimal.ROUND_HALF_DOWN) : null;
	}
}
