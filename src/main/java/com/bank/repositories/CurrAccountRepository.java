package com.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entities.CurrAccount;

@Repository
public interface CurrAccountRepository extends CrudRepository<CurrAccount, Integer> {

	
	public CurrAccount findByUserid(int userid);

	public CurrAccount save(CurrAccount currAccount);

}
