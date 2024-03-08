package com.jsrss.springboot.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.jsrss.springboot.entity.Roles;
import com.jsrss.springboot.entity.User;
import com.jsrss.springboot.service.UserService;

@Controller
@SessionAttributes("MyUsersList") 
public class UserController {

	static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/showAllUsers")
	public String showUsersList(Model model) {
		log.info("-------UserController--showUsersList()---------");
		List<User> ulist = userService.getAllUsers();
		model.addAttribute("MyUsersList", ulist);
		return "usersList";
	}
	
	@PostMapping("/activateDeactivateUser")
	public String activateDeactivateUser(@RequestParam("username") String username, 
			@RequestParam("active") Integer active, Model model) {
		log.info("-------UserController--activateDeactivateUser()---------");
		log.debug("Username from request param : {} ", username);
		log.debug("Active from request param : {} ", active);
		userService.activateDeactivateUser(username, (active == 1 ? 0 : 1));
		List<User> ulist = userService.getAllUsers();
		model.addAttribute("MyUsersList", ulist);
		return "usersList";
	}
	
	@PostMapping("/modifyRolesForm")
	public String modifyRolesForm(@RequestParam("username") String username, Model model) {
		log.info("-------UserController--modifyRolesForm()---------");
		log.debug("Username from request param : {} ", username);
		List<String> roles = userService.getAllUserRoles(username);
		Roles myRoles = new Roles();
		myRoles.setUsername(username);
		myRoles.setRoles(roles);
		model.addAttribute("myroles", myRoles);
		return "editRoles";
	}
	
	@PostMapping("/updateUserRoles")
	public String updateUserRoles(@Valid @ModelAttribute("myroles") Roles roles, BindingResult result, Model model,
			HttpServletRequest req) {
			log.info("-------UserController--updateUserRoles()---------");
			log.info(roles.getUsername());
			roles.getRoles().forEach(role -> log.info(role));
			userService.updateUserRoles(roles);
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
	
	@ModelAttribute("rolesList")
	public Map<String, String> getNumbersList() {
		Map<String, String> map = new TreeMap<>();
		map.put("ROLE_CUSTOMER", "Customer");
		map.put("ROLE_STOREKEEPER", "Storekeeper");
		map.put("ROLE_ADMIN", "Admin");
		return map;
	}
}