package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.response.AlarmChatDTO;
import com.shinhan.heehee.service.AlarmService;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
	// DB 조회 후 소켓 연결
	
	// 미확인 알림 전체 조회
	@ResponseBody
	@GetMapping("/alarmAll")
	public List<AlarmChatDTO> alarmList() {
		
		List<AlarmChatDTO> alarmList = alarmService.alarmList();
		System.out.println("알림 리스트 확인 : " + alarmList);
		
		return alarmList;
	}
	
}