package com.shinhan.heehee.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.heehee.dto.response.AlarmChatDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;
import com.shinhan.heehee.service.AlarmService;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	// 알림 전체 조회
	@ResponseBody
	@GetMapping("/alarmAll")
	public List<AlarmChatDTO> alarmList(Principal principal) {
		String userId = "";
		
		// 로그인 유저 아이디 받기
		if (principal != null) userId = principal.getName();
		
		List<AlarmChatDTO> alarmList = alarmService.alarmList(userId);
		return alarmList;
	}
	
	// 미확인 알림 조회
	@ResponseBody
	@GetMapping("/alarmUnck")
	public List<AlarmChatDTO> alarmUnck(Principal principal) {
		String userId = "";
		
		// 로그인 유저 아이디 받기
		if (principal != null) userId = principal.getName();
		
		List<AlarmChatDTO> alarmUnck = alarmService.alarmUnck(userId);
		return alarmUnck;
	}
	
	// 알림 확인 시 N => Y 업데이트 (마지막 알림이 조회되므로 전부 다 변경하지 않으면 N인 값이 계속 조회됨)
	@PostMapping("/alarmUpdate/{alNum}")
	@ResponseBody
	public int alarmUpdate(@PathVariable("alNum") int alNum) {
		int result = alarmService.alarmUpdate(alNum);
		
		return result;
	}
	
	
	/*
	 @PostMapping("/app/alarmInsert")
	 public int alarmInsert(AlarmDTO alarmDTO) {
		 int result = alarmService.alarmInsert(alarmDTO);
		 
		 return result;
	 }
	 */
	
	// 알림 웹소켓
	@MessageMapping("/alarm/{fromUser}")
	public void handleBid(AlarmDTO alarmDto, @DestinationVariable("fromUser") String fromUser) {
		alarmDto.setId(fromUser); // 알림 받을 유저
		alarmService.alarmInsert(alarmDto); // 알림 생성
		int alarmCnt = alarmService.alarmCount(fromUser); // 받은 알림 수
		
		messagingTemplate.convertAndSend("/topic/alarm/" + fromUser, alarmCnt);
	}
	
}