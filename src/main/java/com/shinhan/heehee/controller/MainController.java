package com.shinhan.heehee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/main")
	public String main() {
		return "/main/main";
	}
	
	@GetMapping("/auction")
	public String auction() {
		return "/main/auction";
	}
}
