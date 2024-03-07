package com.jsrss.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jsrss.springboot.entity.Book;
import com.jsrss.springboot.entity.User;
import com.jsrss.springboot.service.UserService;

@Controller
@SessionAttributes("MyUsersList") 
public class UserController {

	static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/showAllUsers")
	public String showBooksList(Model model) {
		log.info("-------BookController--showBooksList()---------");
		List<User> ulist = userService.getAllUsers();
		model.addAttribute("MyUsersList", ulist);
		return "usersList";
	}
	
	@GetMapping("/showRegister")
	public String showRegisterPage(Model model) {
		log.info("** showRegisterPage **");
		User myuser = new User();
		model.addAttribute("myuser", myuser);
		return "register";
	}

	@PostMapping("/registerMyUser")
	public String registerUser(@ModelAttribute("myuser") User user, BindingResult bindingResult, Model model) {
		log.info("** registerUser **");
		// Do Validations
		userService.registerUser(user);
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpSession session) {
		log.info("** loginPage **");
		return "mylogin";
	}
	
	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		log.info("** logoutPage **");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}