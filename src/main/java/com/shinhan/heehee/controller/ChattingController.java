package com.shinhan.heehee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dto.request.ChatMessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.ChattingService;

@Controller
@RequestMapping("/chatting")
public class ChattingController {

	@Autowired
	private ChattingService cService;

	@Autowired
	private AWSS3Service s3Service;

	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	String userId = "";
	
	// 채팅 페이지
	@GetMapping
	public String chatting(Model model, Principal principal) {
		if(principal != null) userId = principal.getName();
		// model에 담을 것: 유저별 채팅방 목록
		model.addAttribute("roomList", cService.getRoomList(userId));
		model.addAttribute("userId", userId);
		return "chatting/chatting";
	}

	// 채팅방 목록 조회
	@GetMapping(value = "/roomList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ChatRoomDTO> getRoomList(Principal principal) {
		if(principal != null) userId = principal.getName();
		return cService.getRoomList(userId); // 로그인 유저 Id 전달
	}

	// 채팅방 목록에서 채팅방 클릭 시 해당 채팅방의 판매 물품 정보, 유저 계좌 및 포인트 정보, 메시지 목록 조회
	@GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public RoomDetailDTO getRoomDetail(@PathVariable("id") int chatRoomId, Principal principal) {
		if(principal != null) userId = principal.getName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chatRoomId", chatRoomId);
		map.put("loginUserId", userId);
		return cService.getRoomDetail(map); // 채팅방 번호와 로그인 유저 Id 전달
	}

	// 선택한 채팅방에서 상대 메세지 읽음 체크
	// 업데이트 성공 시 1 반환
	@PutMapping("/read")
	@ResponseBody
	public int updateReadCheck(@RequestBody Map<String, Object> map) {
		return cService.updateReadCheck(map);
	}
	
	// 선택한 채팅방 -> 가격 수정 모달 -> 수정하기 눌렀을때 가격 수정
	// 업데이트 성공 시 1 반환
	@PutMapping("/price")
	@ResponseBody
	public int updatePrice(@RequestBody Map<String, Object> map) {
		return cService.updatePrice(map);
	}
	
	// 선택한 채팅방 -> 포인트 충전 모달 -> 충전하기 눌렀을때 포인트 수정
    // 업데이트 성공 시 1 반환
	@PutMapping("/point")
	@ResponseBody
	public int updatePoint(@RequestBody Map<String, Object> map) {
		return cService.updatePoint(map);
	}
	
	@PostMapping("/reserve")
	@ResponseBody
	public void reserve(@RequestBody Map<String, Object> map) {
		cService.reserve(map);
	}
	
	// 메시지(+이미지) insert
	// (1) 메시지 전송
	@PostMapping("/message")
	@ResponseBody
	public String insertMessage(@RequestBody ChatMessageDTO messageDTO) throws IOException {
		System.out.println(messageDTO);
		cService.insertMessage(messageDTO);
		return "test";
	}

	// (2) 사진 전송
	@PostMapping("/image")
	@ResponseBody
	public void insertImage(@RequestPart(required = false) ChatMessageDTO messageDTO,
			@RequestPart(required = false) List<MultipartFile> imgs) throws IOException {
		if(imgs!=null && !imgs.isEmpty()) {
			String filePath ="images/chat/";
			s3Service.uploadObject(imgs, filePath);
			for(MultipartFile img : imgs) {
				if(img!=null && !img.isEmpty()) {
					System.out.println(img.getOriginalFilename());
					cService.insertMsgImg(messageDTO, img);
				}
			}
		}
	}
	
	

	// 소켓: 메시지(+이미지) insert
	// @SendTo 대신 converAndSend 사용
	@MessageMapping("/chat")
	public void sendMessage(ChatMessageDTO message) {
		// 메세지 Insert 로직 구현 필요
		// 우선 텍스트만
		cService.insertMessage(message);
		
		messagingTemplate.convertAndSend("/topic/chatroom/"+ message.getRoomId(), message);
	}
}
