package com.jsrss.springboot.repository;

import java.util.List;

import com.jsrss.springboot.entity.User;

public interface UserDao {
	public void registerUser(User user);
	public void addDefaultRole(User user);
	public List<User> getAllUsers();
}