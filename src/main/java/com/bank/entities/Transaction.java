package com.bank.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="transactionid")
	private int transactionId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "transactiontype")
	private String transactionType;

	@Column(name = "amount")
	private double amount;

	@Column(name = "newbalance")
	private double newBalance;

	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	public Transaction() {}

	public Transaction(String accountNumber, String transactionType, double amount,
			double newBalance, LocalDateTime dateTime) {
		super();
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.newBalance = newBalance;
		this.dateTime = dateTime;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", transactionType="
				+ transactionType + ", amount=" + amount + ", newBalance=" + newBalance + ", dateTime=" + dateTime
				+ "]";
	}
	
	

}
