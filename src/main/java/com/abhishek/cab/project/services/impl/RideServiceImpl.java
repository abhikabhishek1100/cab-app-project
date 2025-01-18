package com.abhishek.cab.project.services.impl;


import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abhishek.cab.project.dto.RideRequestDto;
import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.entities.Rider;
import com.abhishek.cab.project.entities.enums.RideRequestStatus;
import com.abhishek.cab.project.entities.enums.RideStatus;
import com.abhishek.cab.project.exceptions.ResourceNotFoundException;
import com.abhishek.cab.project.repositories.RideRepository;
import com.abhishek.cab.project.services.RideRequestService;
import com.abhishek.cab.project.services.RideService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
	
	 private final RideRepository rideRepository;
	    private final RideRequestService rideRequestService;
	    private final ModelMapper modelMapper;

	    @Override
	    public Ride getRideById(Long rideId) {
	        return rideRepository.findById(rideId)
	                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: "+rideId));
	    }


	    @Override
	    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
	        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

	        Ride ride = modelMapper.map(rideRequest, Ride.class);
	        ride.setRideStatus(RideStatus.CONFIRMED);
	        ride.setDriver(driver);
	        ride.setOtp(generateRandomOTP());
	        ride.setId(null);

	        rideRequestService.update(rideRequest);
	        return rideRepository.save(ride);
	    }

	    @Override
	    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
	        ride.setRideStatus(rideStatus);
	        return rideRepository.save(ride);
	    }

	    @Override
	    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
	        return rideRepository.findByRider(rider, pageRequest);
	    }

	    @Override
	    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
	        return rideRepository.findByDriver(driver, pageRequest);
	    }

	    private String generateRandomOTP() {
	        Random random = new Random();
	        int otpInt = random.nextInt(10000);  //0 to 9999
	        return String.format("%04d", otpInt);
	    }

}
