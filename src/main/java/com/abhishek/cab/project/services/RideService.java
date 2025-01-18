package com.abhishek.cab.project.services;

import com.abhishek.cab.project.dto.RideRequestDto;
import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.entities.Rider;
import com.abhishek.cab.project.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
	Ride getRideById(Long rideId);

	Ride createNewRide(RideRequest rideRequest, Driver driver);

	Ride updateRideStatus(Ride ride, RideStatus rideStatus);

	Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

	Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

}
