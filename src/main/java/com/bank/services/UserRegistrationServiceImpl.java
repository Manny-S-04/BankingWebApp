package com.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.User;
import com.bank.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public User findEmail(String email) {

		return userRepository.findEmail(email);
	}

	@Override
	public User login(String email, String password) {

		return userRepository.login(email, password);
	}

}
