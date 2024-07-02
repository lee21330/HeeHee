package com.shinhan.heehee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dao.ChattingDAO;
import com.shinhan.heehee.dto.request.ChatMessageDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;

@Service
@Transactional
public class ChattingService {
	
	@Autowired
	private ChattingDAO cDao;
	
	@Autowired
	private AlarmDAO alarmDao;
	
	@Autowired
	private AWSS3Service s3Service;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Transactional(readOnly = true)
	public List<ChatRoomDTO> getRoomList(String userId){
		return cDao.getRoomList(userId);
	}
	
	@Transactional(readOnly = true)
	public RoomDetailDTO getRoomDetail(Map<String, Object> map){
		return cDao.getRoomDetail(map);
	}

	public int updateReadCheck(Map<String, Object> map) {
		return cDao.updateReadCheck(map);
	}
	
	public int updatePrice(Map<String, Object> map) {
		return cDao.updatePrice(map);
	}
	
	public int updatePoint(Map<String, Object> map) {
		return cDao.updatePoint(map);
	}
	
	@Transactional
	public void saveMessage(ChatMessageDTO message) {
		if (message.getImgs() != null && !message.getImgs().isEmpty()) {
			List<String> imgs = message.getImgs();
			
			for(String img : imgs) {
				message.setContent("[img_asdfzv] " + img);
	    		cDao.insertChatMsg(message);
	    		
	    		int msgId=message.getMsgId();
	    		
	    		message.setMsgId(msgId);
	    		message.setContent(img);
	    		cDao.insertChatImg(message);
	        }
		} else {
			cDao.insertMessage(message);
		}
		
		if(message.getReadCheck()=="N") {
            AlarmDTO alarmDTO = new AlarmDTO();
            
            String receiverId = message.getReceiver();
    		
    		alarmDTO.setId(receiverId);
    		alarmDTO.setCateNum(1); // 알림 분류 코드 (채팅)
    		alarmDTO.setReqSeq(message.getMsgId());
    		alarmDTO.setAlContent("새로운 메시지가 있습니다.");
    		
    		alarmDao.alarmInsert(alarmDTO);
    		
    		int alarmCnt = alarmDao.alarmCount(receiverId);
    		messagingTemplate.convertAndSend("/topic/alarm/" + receiverId, alarmCnt);
		}
	}
	
    //약속 잡기 / 결제
	@Transactional
	public void reserve(Map<String, Object> map) {
		cDao.updateReserve(map);
		cDao.insertDeal(map);
	}

	//약속 취소
	@Transactional
	public void cancelReserve(Map<String, Object> map) {
		cDao.cancelReserve(map);
		cDao.deleteDeal(map);
	}

}
