package com.shinhan.heehee.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dto.request.MessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;
import com.shinhan.heehee.dto.response.RoomMessageDTO;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.ChattingService;

@Controller
public class ChattingController {

	@Autowired
	private ChattingService cService;

	@Autowired
	private AWSS3Service s3Service;

	@Autowired
	SimpMessagingTemplate messagingTemplate;

	// 채팅 페이지
	@GetMapping("/chatting")
	public String chatting(Model model) {
		// model에 담을 것: 유저별 채팅방 목록
		model.addAttribute("roomList", cService.getRoomList("b"));
		return "chatting/chatting";
	}

	// 채팅방 목록 조회
	// 추후 로그인 유저 정보 받는 부분 수정하기
	@GetMapping(value = "/chatting/roomList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ChatRoomDTO> getRoomList() {
		// System.out.println(cService.getRoomList("a"));
		return cService.getRoomList("b"); // 로그인 유저 Id 전달
	}

	// 채팅방 목록에서 채팅방 클릭 시 해당 채팅방의 판매 물품 정보, 메시지 목록 조회
	// 추후 로그인 유저 정보 수정하기
	@GetMapping(value = "/chatting/{id}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public RoomDetailDTO getRoomDetail(@PathVariable("id") int chatRoomId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chatRoomId", chatRoomId);
		map.put("loginUserId", "b");
		return cService.getRoomDetail(map); // 채팅방 번호와 로그인 유저 Id 전달
	}

	// 선택한 채팅방에서 상대 메세지 읽음 체크
	// 업데이트 성공 시 1 반환
	// 추후 로그인 유저 정보 수정하기
	@PutMapping("/chatting/read")
	@ResponseBody
	public int updateReadCheck(@RequestBody Map<String, Object> map) {
		return cService.updateReadCheck(map);
	}
	
	// 선택한 채팅방 -> 가격 수정 모달 -> 수정하기 눌렀을때 가격 수정
	// 업데이트 성공 시 1 반환
	// 추후 로그인 유저 정보 수정하기
	@PutMapping("/chatting/price")
	@ResponseBody
	public int updatePrice(@RequestBody Map<String, Object> map) {
		//System.out.println("ReceivedMap:" + map);
		return cService.updatePrice(map);
	}

	// 메시지(+이미지) insert
	// 추후 로그인 유저 정보 수정하기
	// (1) 메시지 전송
	@PostMapping("/chatting/message")
	@ResponseBody
	public String insertMessage(@RequestBody MessageDTO messageDTO) throws IOException {
		System.out.println(messageDTO);
		cService.insertMessage(messageDTO);
		return "test";
	}

	// (2) 사진 전송
	@PostMapping("/chatting/Image")
	@ResponseBody
	public String insertImage(@RequestPart(required = false) MessageDTO messageDTO,
			@RequestPart(required = false) MultipartFile img) throws IOException {
		if(img!=null && !img.isEmpty()) {
			cService.insertMsgImg(messageDTO, img);
			String filePath ="images/chat/";
			s3Service.uploadObject(img, filePath + img.getOriginalFilename());
			return "test1";
		}
		return "test2";
	}

	// 소켓: 메시지(+이미지) insert
	@MessageMapping("/message/{roomId}")
	public void sendMessage(@DestinationVariable("roomId") int roomId, RoomMessageDTO message) {
		// 메세지 Insert 로직 구현 필요
		messagingTemplate.convertAndSend("/topic/chatroom/" + roomId, message);
	}
}
