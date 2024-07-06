package com.shinhan.heehee.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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

import com.shinhan.heehee.config.WebSocketEventListener;
import com.shinhan.heehee.dto.request.ChatMessageDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;
import com.shinhan.heehee.service.AWSS3Service;
import com.shinhan.heehee.service.AlarmService;
import com.shinhan.heehee.service.ChattingService;
import com.shinhan.heehee.service.MainService;

@Controller
@RequestMapping("/chatting")
public class ChattingController {

	@Autowired
	private ChattingService cService;
	
	@Autowired
	private MainService mainservice;
	
	@Autowired
	private AlarmService alarmService;

	@Autowired
	private AWSS3Service s3Service;

	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    private WebSocketEventListener webSocketEventListener;
	
	// 채팅 페이지
	@GetMapping
	public String chatting(Model model, Principal principal) {
		List<CategoryDTO> mainCateList = mainservice.mainCateList(); // 카테고리 서비스 호출
		
		String userId = "";
		if (principal != null)
			userId = principal.getName();
		
		int alarmCount = alarmService.alarmCount(userId);

		// model에 담을 것: 유저별 채팅방 목록
		model.addAttribute("roomList", cService.getRoomList(userId));
		model.addAttribute("userId", userId);
		model.addAttribute("mainCateList", mainCateList);
		model.addAttribute("alarmCount", alarmCount); // 알림 개수
		return "chatting/chatting";
	}

	// 채팅방 목록 조회
	@GetMapping(value = "/roomList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ChatRoomDTO> getRoomList(Principal principal) {
		String userId = "";
		if (principal != null)
			userId = principal.getName();
		//System.out.println(cService.getRoomList(userId));
		return cService.getRoomList(userId); // 로그인 유저 Id 전달
	}

	// 채팅방 목록에서 채팅방 클릭 시 해당 채팅방의 판매 물품 정보, 유저 계좌 및 포인트 정보, 메시지 목록 조회
	@GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public RoomDetailDTO getRoomDetail(@PathVariable("id") int chatRoomId, Principal principal) {
		String userId = "";
		if (principal != null)
			userId = principal.getName();
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

	//약속 잡기 / 결제
	@PostMapping("/reserve")
	@ResponseBody
	public void reserve(@RequestBody Map<String, Object> map) {
		cService.reserve(map);
	}
	
	//약속 취소
    @PostMapping("/reserve/cancel")
	@ResponseBody
	public void cancelReserve(@RequestBody Map<String, Object> map) {
		cService.cancelReserve(map);
	}
    
    //채팅방 생성: 판매자와 채팅 클릭 시
    @PostMapping("/seller")
    @ResponseBody
	public int insertChatRoom(@RequestBody Map<String, Object> map) {
		return cService.insertChatRoom(map);
    }
    
   //채팅방 생성: 경매 낙찰 후 채팅 클릭 시
    @PostMapping("/auction")
    @ResponseBody
	public int insertAuctionChat(@RequestBody Map<String, Object> map) {
		return cService.insertAuctionChat(map);
    }

    //이미지 업로드: js에서 사용
	@PostMapping("/upload/image")
	@ResponseBody
	public List<String> uploadImages(@RequestPart List<MultipartFile> imgs) throws IOException {

		ArrayList<String> imgNames = new ArrayList<String>();

		if (imgs != null && !imgs.isEmpty()) {
			for (MultipartFile img : imgs) {
				try {
					String imgName = s3Service.uploadOneObject(img, "images/chat/");
					imgNames.add(imgName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return imgNames;
	}
	
	// 소켓: 메시지(+이미지) insert
	// @SendTo 대신 convertAndSend 사용
	@MessageMapping("/chat")
	public void sendMessage(ChatMessageDTO message) throws IOException {
		int subscribeCount = webSocketEventListener.getSubscribersCount("/topic/chatroom/" + message.getRoomId());
		
		if(subscribeCount>=2) {
			message.setReadCheck("Y");
		} else {
			message.setReadCheck("N");
		}
		
		cService.saveMessage(message);

		messagingTemplate.convertAndSend("/topic/chatroom/" + message.getRoomId(), message);
	}
	
	@MessageMapping("/joinRoom")
	public void joinRoom(Map<String,Object> map) throws IOException {
		messagingTemplate.convertAndSend("/topic/chatroom/" + map.get("roomId"), map);
	}
}