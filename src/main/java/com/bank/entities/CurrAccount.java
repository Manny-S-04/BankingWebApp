package com.bank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curr_account")
public class CurrAccount{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int currid;
	
	@Column(name = "curr_account_number")
	private String curr_account_number;

	@Column(name = "balance")
	private double balance;

	@Column(name = "userid")
	private int userid;

	public CurrAccount() {
	}

	public CurrAccount(String curr_account_number, double balance, int userid) {
		super();
		this.curr_account_number = curr_account_number;
		this.balance = balance;
		this.userid = userid;
	}

	public String getCurr_account_number() {
		return curr_account_number;
	}

	public void setCurr_account_number(String curr_account_number) {
		this.curr_account_number = curr_account_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "CurrAccount [curr_account_number=" + curr_account_number + ", balance=" + balance + ", userid=" + userid
				+ "]";
	}

}