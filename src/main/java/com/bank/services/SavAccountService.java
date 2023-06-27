package com.bank.services;

import org.springframework.stereotype.Service;

import com.bank.entities.SavAccount;


@Service
public interface SavAccountService {

	public SavAccount deposit(double deposit, int userid);

	public SavAccount findByUserid(int userid);
	
	public SavAccount save(SavAccount savAccount);

}
