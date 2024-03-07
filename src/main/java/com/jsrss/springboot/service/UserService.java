package com.jsrss.springboot.service;

import java.util.List;

import com.jsrss.springboot.entity.User;

public interface UserService {
	public void registerUser(User user);
	public List<User> getAllUsers();
}