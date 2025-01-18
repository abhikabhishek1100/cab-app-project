package com.abhishek.cab.project.strategies;

import java.util.List;

import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.RideRequest;

public interface DriverMatchingStrategy {
	 List<Driver> findMatchingDriver(RideRequest rideRequest);

}
