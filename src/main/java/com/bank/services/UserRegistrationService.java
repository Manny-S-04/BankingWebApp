package com.bank.services;

import org.springframework.stereotype.Service;

import com.bank.entities.User;


@Service
public interface UserRegistrationService {
	
	//public boolean register(String firstname, String lastname, String email, String password);
	
	public User save(User user);
	
	public User findEmail(String email);
	
	public User login(String email, String password);

}
