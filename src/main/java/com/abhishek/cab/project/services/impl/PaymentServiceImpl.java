package com.abhishek.cab.project.services.impl;

import org.springframework.stereotype.Service;

import com.abhishek.cab.project.entities.Payment;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.enums.PaymentStatus;
import com.abhishek.cab.project.exceptions.ResourceNotFoundException;
import com.abhishek.cab.project.repositories.PaymentRepository;
import com.abhishek.cab.project.services.PaymentService;
import com.abhishek.cab.project.strategies.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }


}
