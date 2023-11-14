package com.financetracker.userservice.service;

import java.util.List;

import com.financetracker.userservice.modal.User;

public interface UserService {
	public List<User> getAllUsers();

	public User createUser(User user);
}
