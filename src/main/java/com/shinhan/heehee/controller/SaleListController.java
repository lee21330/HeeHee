package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.service.SaleListService;

@Controller
public class SaleListController {

	@Autowired
	SaleListService salelistservice;
	
	@GetMapping("/saleList/{user_id}")
	public String home(@PathVariable("user_id") String userId, Model model) {
		model.addAttribute("info", salelistservice.saleList(userId));
		model.addAttribute("prodImg",salelistservice.prodImg(userId));
		return "/mypage/saleList";
	}
}
