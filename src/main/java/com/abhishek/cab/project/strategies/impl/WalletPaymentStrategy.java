package com.abhishek.cab.project.strategies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.cab.project.entities.Driver;
import com.abhishek.cab.project.entities.Payment;
import com.abhishek.cab.project.entities.Rider;
import com.abhishek.cab.project.entities.enums.PaymentStatus;
import com.abhishek.cab.project.entities.enums.TransactionMethod;
import com.abhishek.cab.project.repositories.PaymentRepository;
import com.abhishek.cab.project.services.WalletService;
import com.abhishek.cab.project.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

//Rider had 232, Driver had 500
//Ride cost is 100, commission = 30
//Rider -> 232-100 = 132
//Driver -> 500 + (100 - 30) = 570

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

	private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(),
                driversCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

}
