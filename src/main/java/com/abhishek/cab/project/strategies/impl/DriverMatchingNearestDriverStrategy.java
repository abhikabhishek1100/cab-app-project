package com.abhishek.cab.project.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.repositories.DriverRepository;
import com.abhishek.cab.project.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

	  private final DriverRepository driverRepository;

	    @Override
	    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
	        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
	    }

}
