package com.abhishek.cab.project.services.impl;

import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.exceptions.ResourceNotFoundException;
import com.abhishek.cab.project.repositories.RideRequestRepository;
import com.abhishek.cab.project.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
	private final RideRequestRepository rideRequestRepository;

	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		return rideRequestRepository.findById(rideRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
	}

	@Override
	public void update(RideRequest rideRequest) {
		rideRequestRepository.findById(rideRequest.getId()).orElseThrow(
				() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequest.getId()));
		rideRequestRepository.save(rideRequest);
	}

}
