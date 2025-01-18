package com.abhishek.cab.project.services;

import com.abhishek.cab.project.dto.DriverDto;
import com.abhishek.cab.project.dto.RideDto;
import com.abhishek.cab.project.dto.RiderDto;
import com.abhishek.cab.project.entities.Driver;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
	RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);
    
    Driver createNewDriver(Driver driver); 

}
