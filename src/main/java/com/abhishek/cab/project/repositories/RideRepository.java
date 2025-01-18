package com.abhishek.cab.project.repositories;

import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.Rider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository  extends JpaRepository<Ride, Long> {
	Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);


}
