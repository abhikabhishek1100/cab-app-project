package com.abhishek.cab.project.strategies.impl;

import org.springframework.stereotype.Service;

import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.services.DistanceService;
import com.abhishek.cab.project.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

	 private final DistanceService distanceService;
	    private static final double SURGE_FACTOR = 2;

	    @Override
	    public double calculateFare(RideRequest rideRequest) {
	        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
	                rideRequest.getDropOffLocation());
	        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
	    }
	}

