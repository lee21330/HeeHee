package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.shinhan.heehee.service.DealService;

@Controller
public class MyPageController {

	@Autowired
	DealService dealService;

	@GetMapping("/chargepoint")
	public String chargePoint() {
		return "/mypage/pointCharge";
	}

	@GetMapping("/profile")
	public String editProfile() {
		return "/mypage/editProfile";
	}

	@GetMapping("/qnaBoard")
	public String qnaBoard() {
		return "/mypage/qnaBoard";
	}

	@GetMapping("/account")
	public String editAccount() {
		return "/mypage/editAccount";
	}

	@GetMapping("/salelist")
	public String saleList() {
		return "/mypage/saleList";
	}

	@GetMapping("/purchaseList")
	public String purchaseList() {
		return "/mypage/purchaseList";
	}

	@GetMapping("/jjimList")
	public String jjimList() {
		return "/mypage/jjimList";
	}
	@GetMapping("/saledetail")
	public String saledetail() {
		return "/mypage/saleDetail";
	}
	@GetMapping("/purchasedetail")
	public String purchasedetail() {
		return "/mypage/purchaseDetail";
	}
	@GetMapping("/pointList")
	public String pointList() {
		return "/mypage/pointList";
	}

}