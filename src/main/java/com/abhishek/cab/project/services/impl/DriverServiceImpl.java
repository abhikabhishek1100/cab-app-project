package com.abhishek.cab.project.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.abhishek.cab.project.dto.DriverDto;
import com.abhishek.cab.project.dto.RideDto;
import com.abhishek.cab.project.dto.RiderDto;
import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.RideRequest;
import com.abhishek.cab.project.entities.User;
import com.abhishek.cab.project.entities.enums.RideRequestStatus;
import com.abhishek.cab.project.entities.enums.RideStatus;
import com.abhishek.cab.project.exceptions.ResourceNotFoundException;
import com.abhishek.cab.project.repositories.DriverRepository;
import com.abhishek.cab.project.services.DriverService;
import com.abhishek.cab.project.services.PaymentService;
import com.abhishek.cab.project.services.RatingService;
import com.abhishek.cab.project.services.RideRequestService;
import com.abhishek.cab.project.services.RideService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper modelMapper;
	private final PaymentService paymentService;
	private final RatingService ratingService;

	@Override
	@Transactional
	public RideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

		if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException(
					"RideRequest cannot be accepted, status is " + rideRequest.getRideRequestStatus());
		}

		Driver currentDriver = getCurrentDriver();
		if (!currentDriver.getAvailable()) {
			throw new RuntimeException("Driver cannot accept ride due to unavailability");
		}

		Driver savedDriver = updateDriverAvailability(currentDriver, false);

		Ride ride = rideService.createNewRide(rideRequest, savedDriver);
		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);

		Driver driver = getCurrentDriver();
		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
		}

		rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		updateDriverAvailability(driver, true);

		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto startRide(Long rideId, String otp) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException(
					"Ride status is not CONFIRMED hence cannot be started, status: " + ride.getRideStatus());
		}

		if (!otp.equals(ride.getOtp())) {
			throw new RuntimeException("Otp is not valid, otp: " + otp);
		}

		ride.setStartedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

		paymentService.createNewPayment(savedRide);

		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	@Transactional
	public RideDto endRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier");
		}

		if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
			throw new RuntimeException(
					"Ride status is not ONGOING hence cannot be ended, status: " + ride.getRideStatus());
		}

		ride.setEndedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
		updateDriverAvailability(driver, true);

		paymentService.processPayment(ride);

		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	public RiderDto rateRider(Long rideId, Integer rating) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if (!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver is not the owner of this Ride");
		}

		if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
			throw new RuntimeException(
					"Ride status is not Ended hence cannot start rating, status: " + ride.getRideStatus());
		}

		return ratingService.rateRider(ride, rating);

	}

	@Override
	public DriverDto getMyProfile() {
		Driver currentDriver = getCurrentDriver();
		return modelMapper.map(currentDriver, DriverDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Driver currentDriver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(currentDriver, pageRequest)
				.map(ride -> modelMapper.map(ride, RideDto.class));
	}

	@Override
	public Driver getCurrentDriver() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return driverRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Driver not associated with " + "id " + user.getId()));
	}

	@Override
	public Driver updateDriverAvailability(Driver driver, boolean available) {
		driver.setAvailable(available);
		return driverRepository.save(driver);
	}

	@Override
	public Driver createNewDriver(Driver driver) {
		return driverRepository.save(driver);
	}

}
