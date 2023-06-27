package com.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.CurrAccount;
import com.bank.repositories.CurrAccountRepository;

@Service
public class CurrAccountServiceImpl implements CurrAccountService {

	@Autowired
	private CurrAccountRepository accountRepository;

	@Override
	public CurrAccount deposit(double deposit, int userid) {

		CurrAccount currAccount = accountRepository.findByUserid(userid);

		double currentBalance = currAccount.getBalance();

		double newBalance = currentBalance + deposit;

		currAccount.setBalance(newBalance);

		return accountRepository.save(currAccount);

	}

	@Override
	public CurrAccount withdraw(double withdraw, int userid) {

		double balance = accountRepository.findByUserid(userid).getBalance();

		if (withdraw > balance) {

			withdraw = balance;
		}
		
		CurrAccount currAccount = accountRepository.findByUserid(userid);

		double currentBalance = currAccount.getBalance();

		double newBalance = currentBalance - withdraw;
		
		currAccount.setBalance(newBalance);
		
		return accountRepository.save(currAccount);

	}

	@Override
	public CurrAccount findByUserid(int userid) {

		return accountRepository.findByUserid(userid);
	}

	@Override
	public CurrAccount save(CurrAccount currAccount) {

		return accountRepository.save(currAccount);
	}

}
