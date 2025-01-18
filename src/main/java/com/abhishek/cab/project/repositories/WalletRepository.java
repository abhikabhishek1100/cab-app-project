package com.abhishek.cab.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.cab.project.entities.User;
import com.abhishek.cab.project.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{
	Optional<Wallet> findByUser(User user);


}
