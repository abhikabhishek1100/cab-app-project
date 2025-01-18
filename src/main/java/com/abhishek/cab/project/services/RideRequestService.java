package com.abhishek.cab.project.services;

import com.abhishek.cab.project.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);

}
