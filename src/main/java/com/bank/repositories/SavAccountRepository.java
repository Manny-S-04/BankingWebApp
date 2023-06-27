package com.bank.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.SavAccount;

import jakarta.transaction.Transactional;


@Repository
public interface SavAccountRepository extends CrudRepository<SavAccount, Integer> {
	
	@Transactional
	@Modifying
	@Query("UPDATE CurrAccount c SET c.balance = c.balance + :deposit WHERE c.userid = :userid")
	public void deposit(double deposit, int userid);
		
	public SavAccount findByUserid(int userid);
	
	public SavAccount save (SavAccount savAccount);
}
