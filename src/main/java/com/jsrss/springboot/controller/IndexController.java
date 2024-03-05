package com.jsrss.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String showIndexPage() {
		log.info("---------showIndexPage---------");
		return "index";
	}
}