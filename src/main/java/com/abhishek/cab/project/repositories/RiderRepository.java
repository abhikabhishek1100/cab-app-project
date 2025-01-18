package com.abhishek.cab.project.repositories;

import com.abhishek.cab.project.entities.Rider;
import com.abhishek.cab.project.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

	Optional<Rider> findByUser(User user);
}
