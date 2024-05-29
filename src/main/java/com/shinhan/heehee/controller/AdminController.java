package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.heehee.service.TestService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	TestService productservice;
	
	@GetMapping("/main")
	public String admin_main() {
		return "/admin/main";
	}
	
	@GetMapping("/user")
	public String admin_user() {
		return "/admin/user";
	}
}
