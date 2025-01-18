package com.abhishek.cab.project.strategies;

import com.abhishek.cab.project.entities.Payment;

public interface PaymentStrategy  {
	 Double PLATFORM_COMMISSION = 0.3;
	    void processPayment(Payment payment);


}
