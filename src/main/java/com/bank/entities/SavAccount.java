package com.bank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sav_account")
public class SavAccount{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int savid;
	
	@Column(name = "sav_account_number")
	private String sav_account_number;

	@Column(name = "balance")
	private double balance;

	@Column(name = "userid")
	private int userid;

	public SavAccount() {
	}

	public SavAccount(String sav_account_number, double balance, int userid) {
		super();
		this.sav_account_number = sav_account_number;
		this.balance = balance;
		this.userid = userid;
	}

	public String getSav_account_number() {
		return sav_account_number;
	}

	public void setSav_account_number(String sav_account_number) {
		this.sav_account_number = sav_account_number;
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
		return "savAccount [sav_account_number=" + sav_account_number + ", balance=" + balance + ", userid=" + userid
				+ "]";
	}

}