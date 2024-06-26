package com.shinhan.heehee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shinhan.heehee.service.TestService;

import lombok.Setter;

@Controller
public class HomeController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/home")
	public String home(Model model) {
		System.out.println(testService.test());
		List<Map<String,Object>> idArr = testService.mapTest();
		model.addAttribute("arr",idArr);
		System.out.println(idArr);
		for(Map<String,Object> map: idArr) {
			System.out.println(map.get("PW"));
		}
		return "home";
	}
}
