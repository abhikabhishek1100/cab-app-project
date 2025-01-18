package com.abhishek.cab.project.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.abhishek.cab.project.entities.WalletTransaction;
import com.abhishek.cab.project.repositories.WalletTransactionRepository;
import com.abhishek.cab.project.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class WalletTransactionServiceImpl implements WalletTransactionService {

	 private final WalletTransactionRepository walletTransactionRepository;
	    private final ModelMapper modelMapper;

	    @Override
	    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
	        walletTransactionRepository.save(walletTransaction);
	    }


}
