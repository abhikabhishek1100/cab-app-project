package com.abhishek.cab.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.cab.project.entities.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {


}
