package com.jsrss.springboot.service;

import java.util.List;

import com.jsrss.springboot.entity.Roles;
import com.jsrss.springboot.entity.User;

public interface UserService {
	public void registerUser(User user);
	public List<User> getAllUsers();
	public void activateDeactivateUser(String username, int active);
	public List<String> getAllUserRoles(String username);
	public void updateUserRoles(Roles roles);
}