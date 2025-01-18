package com.abhishek.cab.project.services;

import com.abhishek.cab.project.dto.DriverDto;
import com.abhishek.cab.project.dto.RiderDto;
import com.abhishek.cab.project.entities.Ride;

public interface RatingService {

	DriverDto rateDriver(Ride ride, Integer rating);

	RiderDto rateRider(Ride ride, Integer rating);

	void createNewRating(Ride ride);

}
