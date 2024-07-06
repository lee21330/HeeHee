package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.heehee.dto.request.BanUserDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.service.AlarmService;
import com.shinhan.heehee.service.AuctionService;
import com.shinhan.heehee.service.MainService;
import com.shinhan.heehee.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainservice;
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/main")
	public String main(Model model, Principal principal) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // 카테고리 서비스 호출
		
		if(principal != null) {
			model.addAttribute("userId",principal.getName());
			int alarmCount = alarmService.alarmCount(principal.getName());
			model.addAttribute("alarmCount",alarmCount);
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
	
	@GetMapping("/ban")
	public String ban(@RequestParam String userId, Model model) {
		BanUserDTO banUserDTO = userService.banCheck(userId);
		model.addAttribute("banUserDto", banUserDTO);
		return "/common/banUser";
	}
}
