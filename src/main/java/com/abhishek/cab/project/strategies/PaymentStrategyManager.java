package com.abhishek.cab.project.strategies;

import org.springframework.stereotype.Component;

import com.abhishek.cab.project.entities.enums.PaymentMethod;
import com.abhishek.cab.project.strategies.impl.CashPaymentStrategy;
import com.abhishek.cab.project.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

	 private final WalletPaymentStrategy walletPaymentStrategy;
	    private final CashPaymentStrategy cashPaymentStrategy;

	    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
	        return switch (paymentMethod) {
	            case WALLET -> walletPaymentStrategy;
	            case CASH -> cashPaymentStrategy;
	        };
	    }

}
