package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.service.ChattingService;

@Controller
public class ChattingController {
	
	@Autowired
	private ChattingService cService;
	
	
	@GetMapping("/chatting")
	public String chatting(Model model) {
		//model에 담을 것: 유저별 채팅방 목록
		return "chatting/chatting";
	}
	
	//추후 로그인 유저 정보 받는 부분 수정하기
	@GetMapping(value="/chatting/roomList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ChatRoomDTO> getRoomList(){
		//System.out.println(cService.getRoomList("a"));
		return cService.getRoomList("a");
	}
	
}
