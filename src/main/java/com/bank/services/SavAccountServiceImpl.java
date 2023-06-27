package com.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.SavAccount;
import com.bank.repositories.SavAccountRepository;

@Service
public class SavAccountServiceImpl implements SavAccountService {

	@Autowired
	SavAccountRepository accountRepository;

	@Override
	public SavAccount deposit(double deposit, int userid) {

		SavAccount savAccount = accountRepository.findByUserid(userid);

		double currentBalance = savAccount.getBalance();

		double newBalance = currentBalance + deposit;

		savAccount.setBalance(newBalance);

		return accountRepository.save(savAccount);

	}

	@Override
	public SavAccount findByUserid(int userid) {

		return accountRepository.findByUserid(userid);
	}

	@Override
	public SavAccount save(SavAccount savAccount) {
		
		
		return accountRepository.save(savAccount);
	}

}
