package com.challenge.traceip.service;

abstract class DistanceCalculator
{
	
	/**
	 * @see https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
	 * 
	 * Calculate distance between two points in latitude and longitude
	 * Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * @returns Distance in Kilometers
	 */
	public static double distance(double lat1, double lon1, double lat2,
	        double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c; // convert to meters

	    return distance;
	}
}

