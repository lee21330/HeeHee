package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.service.SellerProfileService;

@Controller
public class SellerProfileController {
	
	@Autowired
	SellerProfileService sellerprofileservice;
	
	@GetMapping("/sellerProfile/{id}")
	public String home(@PathVariable("id") String id, Model model) {
		model.addAttribute("sellerinfo", sellerprofileservice.sellerinfo(id));
		return "/used/sellerProfile";
	}
}
