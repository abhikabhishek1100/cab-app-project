package com.abhishek.cab.project.entities;

import com.abhishek.cab.project.entities.enums.PaymentMethod;
import com.abhishek.cab.project.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@OneToOne(fetch = FetchType.LAZY)
	private Ride ride;

	private Double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@CreationTimestamp
	private LocalDateTime paymentTime;


	

}
