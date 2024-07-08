package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.request.BanUserDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.ElasticSyncDTO;
import com.shinhan.heehee.service.AlarmService;
import com.shinhan.heehee.service.AuctionService;
import com.shinhan.heehee.service.ElasticsearchService;
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
	
	@Autowired
	ElasticsearchService elasticsearchService;
	
	@GetMapping("/main")
	public String main(Model model, Principal principal) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // 카테고리 서비스 호출
		String loginId = (principal != null) ? principal.getName() : "admin";
		model.addAttribute("userId",loginId);
		
		int alarmCount = alarmService.alarmCount(loginId);
		model.addAttribute("alarmCount",alarmCount);
	
		
		model.addAttribute("rankProdList", mainservice.rankProdList());
		model.addAttribute("recommandList", mainservice.recommandList(loginId));
		model.addAttribute("recentprodList", mainservice.recentprodList());
		return "/main/main";
	}
	
	
	@GetMapping("/main/search")
	public String search(@RequestParam(value="keyword", required = false) String keyword
						,@RequestParam(value="category", required = false) Integer cateNum
						, Model model, Principal principal) {
		
		List<ElasticSyncDTO> productArr = new ArrayList<>();
		
		if(keyword != null) productArr = elasticsearchService.searchKeyword(keyword);
		if(cateNum != null) productArr = elasticsearchService.searchCategory(cateNum);
		
		model.addAttribute("productArr", productArr);
		model.addAttribute("resultCount", productArr.size());
		
		String loginId = (principal != null) ? principal.getName() : "admin";
		int alarmCount = alarmService.alarmCount(loginId);
		model.addAttribute("alarmCount", alarmCount); // 알림 개수

		List<CategoryDTO> mainCateList = mainservice.mainCateList();
		model.addAttribute("mainCateList", mainCateList); // header 카테고리
		
		return "/main/search";
	}
	
	@GetMapping("/ban")
	public String ban(@RequestParam String userId, Model model) {
		BanUserDTO banUserDTO = userService.banCheck(userId);
		model.addAttribute("banUserDto", banUserDTO);
		return "/common/banUser";
	}
	
	@GetMapping("/bank")
	@ResponseBody
	public List<Map<String,Object>> getBankKind() {
		return userService.getBankKind();
	}
}
