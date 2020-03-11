package com.gestion.stock.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login")
	public String home(HttpServletRequest request, Model model) {
		return "login/login";
	}
	
	@RequestMapping(value = "/failedlogin")
	public String failedLogin() {
		return "login/login";
	}
	@RequestMapping(value = "/home")
	public String Login(Model model) {
		SecurityContext context = SecurityContextHolder.getContext();
	    model.addAttribute("userName", context.getAuthentication().getName());
		return "home/home";
	}
}
