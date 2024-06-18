package com.shinhan.heehee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.heehee.service.AdminBanHistoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminBanHistoryService banHistoryService;
	
	@GetMapping("/main")
	public String admin_main() {
		banHistoryService.searchAll();
		System.out.println();
		return "/admin/main";
	}
	
	@GetMapping("/user")
	public String admin_user() {
		return "/admin/user";
	}
	
	@GetMapping("/ban")
	public String admin_userVan() {
		return "/admin/user-ban";
	}
	
	@GetMapping("/product")
	public String admin_product() {
		return "/admin/product";
	}
	
	@GetMapping("/auction")
	public String admin_auction() {
		return "/admin/auction";
	}
	
	@GetMapping("/category")
	public String admin_category() {
		return "/admin/category";
	}
	
	@GetMapping("/qnaManager")
	public String admin_qnaManager() {
		return "/admin/qnaManager";
	}
	
	@GetMapping("/faqManager")
	public String admin_faqManager() {
		return "/admin/faqManager";
	}
	
	@GetMapping("/questionManager")
	public String admin_questionManager() {
		return "/admin/questionManager";
	}
}