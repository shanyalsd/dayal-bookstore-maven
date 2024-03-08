package com.jsrss.springboot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jsrss.springboot.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemp;

	public void registerUser(User user) {
		String sql="insert into myusers values(?,?,?,?,?,?,?)";
		jdbcTemp.update(sql,user.getUsername(),
				user.getPassword(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPhone(),
				user.getActive());
	}
	
	public void addUserRole(String username, String role) {
		String sql="insert into myroles values(?,?,?)";
		jdbcTemp.update(sql,getMaxUserRoleId() + 1, 
				username,
				role);
	}
	
	@Override
	public void deleteUserRole(String username, String role) {
		String sql="delete from myroles where myusername=? and role=?";
		jdbcTemp.update(sql,username,role);
	}
	
	private long getMaxUserRoleId() {
		String sql="select max(user_role_id) from myroles";
		return jdbcTemp.queryForObject(sql, Long.class);
	}

	@Override
	public List<User> getAllUsers() {
		String sql="select username, firstname, lastname, email, phone, active, (select GROUP_CONCAT(role) from myroles where myusername = myusers.username) as user_roles from myusers";
		List<Map<String, Object>> queryForList = jdbcTemp.queryForList(sql);
		return convertUserData(queryForList);
	}
	
	private List<User> convertUserData(List<Map<String, Object>> listOfMap)
	{
		List<User> userList = new ArrayList<>();
		for(Map<String, Object> map:listOfMap)
		{
			User user = new User();
			user.setUsername(map.get("username").toString());
			user.setFirstname(map.get("firstname").toString());
			user.setLastname(map.get("lastname").toString());
			user.setEmail(map.get("email").toString());
			user.setPhone(map.get("phone").toString());
			user.setActive((Integer)map.get("active"));
			user.setUserRoles(String.valueOf(map.get("user_roles") != null ? map.get("user_roles") : "ROLE_ANONYMOUS"));
			userList.add(user);
		}
		return userList;
	}

	@Override
	public void activateDeactivateUser(String username, int active) {
		String sql="update myusers set active=? where username = ?";
		jdbcTemp.update(sql, active, username);
	}

	@Override
	public List<String> getAllUserRoles(String username) {
		String sql="select role from myroles where myusername = ?";
		return jdbcTemp.queryForList(sql, String.class, username);
	}
}