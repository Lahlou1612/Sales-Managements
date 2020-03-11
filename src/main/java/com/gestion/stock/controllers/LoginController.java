package com.gestion.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(value = "/signin")
	public String home() {
		return "login/login";
	}
}
