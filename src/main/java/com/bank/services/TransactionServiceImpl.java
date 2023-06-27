package com.bank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.Transaction;
import com.bank.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findByAccountNumber(String accountNumber) {

		return transactionRepository.findByAccountNumber(accountNumber);

	}

	@Override
	public Transaction save(Transaction transaction) {

		return transactionRepository.save(transaction);

	}

}
