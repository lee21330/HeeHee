package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shinhan.heehee.service.MyPageService;

@Controller
public class MyPageController {

	@Autowired
	MyPageService mypageservice;

	@GetMapping("/myPage/{user_id}")
	public String searchSaleList(@PathVariable("user_id") String userId, Model model) {
		model.addAttribute("sInfo", mypageservice.saleList(userId));
		model.addAttribute("pInfo", mypageservice.purchaseList(userId));
		model.addAttribute("jInfo", mypageservice.jjimList(userId));
		return "/mypage/myPage";
	}

//	@GetMapping("/saledetail/${sale.productSeq}")
//	public String searchSaleList(@PathVariable("user_id") String userId, Model model) {
//		model.addAttribute("sInfo", mypageservice.saleList(userId));
//		model.addAttribute("pInfo", mypageservice.purchaseList(userId));
//		model.addAttribute("jInfo", mypageservice.jjimList(userId));
//		return "/mypage/myPage";
//	}

}
