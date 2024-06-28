package com.shinhan.heehee.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.heehee.service.AuctionService;
import com.shinhan.heehee.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainservice;
	
	@Autowired
	AuctionService auctionService;
	
	@GetMapping("/main")
	public String main(Model model, Principal principal) {
		
		if(principal != null) {
			System.out.println("아이디: " + principal.getName());
		}
		
		model.addAttribute("rankProdList", mainservice.rankProdList());
		model.addAttribute("recommandList", mainservice.recommandList());
		model.addAttribute("recentprodList", mainservice.recentprodList());
		return "/main/main";
	}
	
	@GetMapping("/auc")
	public String auction(Model model) {
		model.addAttribute("aucList", auctionService.aucProdList());
		return "/main/auction";
	}
}
