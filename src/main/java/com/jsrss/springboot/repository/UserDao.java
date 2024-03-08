package com.jsrss.springboot.repository;

import java.util.List;

import com.jsrss.springboot.entity.User;

public interface UserDao {
	public void registerUser(User user);
	public void addUserRole(String username, String role);
	public void deleteUserRole(String username, String role);
	public List<User> getAllUsers();
	public void activateDeactivateUser(String username, int active);
	public List<String> getAllUserRoles(String username);
}