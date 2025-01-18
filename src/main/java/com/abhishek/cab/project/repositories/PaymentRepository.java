package com.abhishek.cab.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.cab.project.entities.Payment;
import com.abhishek.cab.project.entities.Ride;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{
	 Optional<Payment> findByRide(Ride ride);


}
