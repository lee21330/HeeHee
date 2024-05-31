package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.heehee.service.TestService;

@Controller
public class ProductController {
	
	@Autowired
	TestService productservice;
	
	@GetMapping("/productdetail")
	public String detail() {
		System.out.println(productservice.test());
		return "/used/productdetail";
	}
	
	@GetMapping("/productregi")
	public String regi() {
		System.out.println(productservice.test());
		return "/used/productregi";
	}
}
