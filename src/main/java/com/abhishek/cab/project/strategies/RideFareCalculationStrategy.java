package com.abhishek.cab.project.strategies;

import com.abhishek.cab.project.entities.RideRequest;

public interface RideFareCalculationStrategy {
	
	 double RIDE_FARE_MULTIPLIER = 10;

	    double calculateFare(RideRequest rideRequest);


}
