package com.abhishek.cab.project.entities;

import com.abhishek.cab.project.entities.enums.TransactionMethod;
import com.abhishek.cab.project.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(indexes = {
        @Index(name = "idx_wallet_transaction_wallet", columnList = "wallet_id"),
        @Index(name = "idx_wallet_transaction_ride", columnList = "ride_id")
})
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	private TransactionType transactionType;

	private TransactionMethod transactionMethod;

	@ManyToOne
	private Ride ride;

	private String transactionId;

	@ManyToOne
	private Wallet wallet;

	@CreationTimestamp
	private LocalDateTime timeStamp;

	

}
