package com.abhishek.cab.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class WalletDto {
	
	  private Long id;

	    private UserDto user;

	    private Double balance;

	    private List<WalletTransactionDto> transactions;



}
