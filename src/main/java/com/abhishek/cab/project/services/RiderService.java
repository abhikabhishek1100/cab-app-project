package com.abhishek.cab.project.services;

import com.abhishek.cab.project.dto.DriverDto;
import com.abhishek.cab.project.dto.RideDto;
import com.abhishek.cab.project.dto.RideRequestDto;
import com.abhishek.cab.project.dto.RiderDto;
import com.abhishek.cab.project.entities.Rider;
import com.abhishek.cab.project.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
	RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();


}
