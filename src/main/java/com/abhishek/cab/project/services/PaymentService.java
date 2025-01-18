package com.abhishek.cab.project.services;

import com.abhishek.cab.project.entities.Payment;
import com.abhishek.cab.project.entities.Ride;
import com.abhishek.cab.project.entities.enums.PaymentStatus;

public interface PaymentService {
	
	void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);


}
