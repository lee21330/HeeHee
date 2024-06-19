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
		model.addAttribute("sellerInfo", mypageservice.sellerInfo(userId));
		return "/mypage/myPage";
	}

	@GetMapping("/qnaBoard")
	public String qnaBoard() {
		return "/mypage/qnaBoard";
	}

	@GetMapping("/faqBoard")
	public String faqBoard() {
		return "/mypage/faqBoard";
	}

	@GetMapping("/saledetail/{productSeq}")
	public String saleDetail(@PathVariable("productSeq") int proSeq, Model model) {
		return "/mypage/saleDetail";
	}

	@GetMapping("/purchasedetail/{productSeq}")
	public String purchasedetail(@PathVariable("productSeq") int proSeq, Model model) {
		return "/mypage/purchaseDetail";
	}

	@GetMapping("/jjimList/{productSeq}")
	public String jjimList(@PathVariable("productSeq") int proSeq, Model model) {
		return "/mypage/jjimList";
	}

}
