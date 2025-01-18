package com.abhishek.cab.project.services;

import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.User;
import com.abhishek.cab.project.entities.Wallet;
import com.abhishek.cab.project.entities.enums.TransactionMethod;

public interface WalletService {

	Wallet addMoneyToWallet(User user, Double amount, 
			String transactionId, Ride ride,
			TransactionMethod transactionMethod);

	Wallet deductMoneyFromWallet(User user, Double amount,
			String transactionId, Ride ride,
			TransactionMethod transactionMethod);

	void withdrawAllMyMoneyFromWallet();

	Wallet findWalletById(Long walletId);

	Wallet createNewWallet(User user);

	Wallet findByUser(User user);

}
