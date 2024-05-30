package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.heehee.service.DealService;


@Controller
public class MyPageController {


	@Autowired
	DealService dealService;
	
	@GetMapping("/mypage")
	public String home() {
		System.out.println(dealService.deal());
		return "/mypage/myPage";
	}
	
	@GetMapping("/pointCharge")
	public String chargePoint() {
		return "/mypage/pointCharge";
	}
	
	@GetMapping("/userUpdate")
	public String updateUser() {
		return "/mypage/pointCharge";
	}
	
}
