package com.gestion.stock.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@RequestMapping(value = "/")
//	public String home() {
//		return "home/home";
//	}
	@RequestMapping(value = "/blank")
	public String homeBlank() {
		return "blank/blank";
	}	
}
