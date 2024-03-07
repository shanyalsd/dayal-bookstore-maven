package com.jsrss.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsrss.springboot.entity.User;
import com.jsrss.springboot.repository.UserDao;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
		userDao.addDefaultRole(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}