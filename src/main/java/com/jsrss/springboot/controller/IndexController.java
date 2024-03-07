package com.jsrss.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String showIndexPage(Model model, HttpSession session) {
		log.info("---------showIndexPage---------");
		List<String> myroles = getUserRoles();
		session.setAttribute("MY_ROLES", myroles);
		return "index";
	}
	
	private List<String> getUserRoles() {
		List<String> myroles = new ArrayList<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Collection<GrantedAuthority> col = (Collection<GrantedAuthority>) auth.getAuthorities();
			for (GrantedAuthority gauth : col)
				myroles.add(gauth.getAuthority());
		}
		return myroles;
	}
}