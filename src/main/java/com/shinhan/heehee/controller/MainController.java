package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.heehee.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainservice;
	
	@GetMapping("/main")
	public String main(Model model) {
		model.addAttribute("rankProdList", mainservice.rankProdList());
		model.addAttribute("recommandList", mainservice.recommandList());
		model.addAttribute("recentprodList", mainservice.recentprodList());
		return "/main/main";
	}
<<<<<<< HEAD
=======
	
	@GetMapping("/auc")
	public String auction(Model model) {
		model.addAttribute("aucList", auctionService.aucProdList());
		return "/main/auction";
	}
>>>>>>> branch 'feat/hyunsang' of https://github.com/Sh03Team05/HeeHee.git
}
