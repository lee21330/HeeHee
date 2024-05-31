package com.shinhan.heehee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingController {
	
	@GetMapping("/chatting")
	public String chatting(Model model) {
		//model에 담을 것: 유저별 채팅방 목록
		return "chatting/chatting";
	}
}
