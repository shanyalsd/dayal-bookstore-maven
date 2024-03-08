package com.jsrss.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsrss.springboot.entity.Roles;
import com.jsrss.springboot.entity.User;
import com.jsrss.springboot.repository.UserDao;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String DEFAULT_ROLE = "ROLE_CUSTOMER";
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void registerUser(User user) {
		log.debug(user.getPassword());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		log.debug(user.getPassword());
		user.setActive(1);
		userDao.registerUser(user);
		userDao.addUserRole(user.getUsername(), DEFAULT_ROLE);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void activateDeactivateUser(String username, int active) {
		userDao.activateDeactivateUser(username, active);
	}

	@Override
	public List<String> getAllUserRoles(String username) {
		return userDao.getAllUserRoles(username);
	}

	@Override
	public void updateUserRoles(Roles roles) {
		List<String> allUserRoles = userDao.getAllUserRoles(roles.getUsername());
		for(String uirole:roles.getRoles())
		{
			if(!allUserRoles.contains(uirole))
			{
				userDao.addUserRole(roles.getUsername(), uirole);
			}
		}
		for(String dbrole:allUserRoles)
		{
			if(!roles.getRoles().contains(dbrole))
			{
				userDao.deleteUserRole(roles.getUsername(), dbrole);
			}
		}
	}
}