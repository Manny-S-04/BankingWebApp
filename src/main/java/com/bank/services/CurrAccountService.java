package com.bank.services;

import org.springframework.stereotype.Service;

import com.bank.entities.CurrAccount;
import com.bank.entities.SavAccount;

@Service
public interface CurrAccountService {
	
	public CurrAccount deposit(double deposit, int userid);
	
	public CurrAccount withdraw(double withdraw, int userid);
		
	public CurrAccount findByUserid(int userid);
	
	public CurrAccount save(CurrAccount currAccount);
	
	
}
