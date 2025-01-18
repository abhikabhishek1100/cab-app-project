package com.abhishek.cab.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Rating;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.Rider;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByRider(Rider rider);

	List<Rating> findByDriver(Driver driver);

	Optional<Rating> findByRide(Ride ride);

}
