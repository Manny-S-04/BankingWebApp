package com.bank.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.entities.Transaction;

@Service
public interface TransactionService {
	
	public List<Transaction> findByAccountNumber(String accountNumber);

	public Transaction save(Transaction transaction);
}
