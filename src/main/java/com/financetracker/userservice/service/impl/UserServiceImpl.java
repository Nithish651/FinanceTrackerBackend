package com.financetracker.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financetracker.userservice.modal.User;
import com.financetracker.userservice.repository.UserRepository;
import com.financetracker.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



}
