package com.shinhan.heehee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// 알림 전체 조회
	@ResponseBody
	@GetMapping("/alarmAll/{userId}")
	public List<AlarmChatDTO> alarmList(@PathVariable("userId") String userId) {
		List<AlarmChatDTO> alarmList = alarmService.alarmList(userId);
		
		// System.out.println("알림 전체 조회 >>>> " + alarmList);
		// System.out.println("로그인 userId 확인 >>>> " + userId);
		
		return alarmList;
	}
	
	// 미확인 알림 조회
	@ResponseBody
	@GetMapping("/alarmUnck/{userId}")
	public List<AlarmChatDTO> alarmUnck(@PathVariable("userId") String userId) {
		List<AlarmChatDTO> alarmUnck = alarmService.alarmUnck(userId);
		
		// System.out.println("미확인 알림 조회 >>>> " + alarmUnck);
		// System.out.println("로그인 userId 확인 >>>> " + userId);
		
		return alarmUnck;
	}
	
	// 알림 확인 시 N => Y 업데이트
	// 마지막 알림이 조회되므로 전부 다 변경하지 않으면 N인 값이 계속 조회됨
	@PostMapping("/alarmCheck")
	public String alarmCheck() {
		System.out.println("업데이트 url 경로 테스트");
		
		return "/common/header";
	}
	
}