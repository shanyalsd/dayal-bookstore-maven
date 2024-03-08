package com.jsrss.springboot.entity;

import java.util.List;

public class Roles {

	private String username;
	private List<String> roles;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}