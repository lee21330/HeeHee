package com.shinhan.heehee.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.ChattingDAO;
import com.shinhan.heehee.dto.request.ChatImageDTO;
import com.shinhan.heehee.dto.request.ChatMessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;

@Service
@Transactional
public class ChattingService {
	
	@Autowired
	private ChattingDAO cDao;
	
	@Autowired
	private AWSS3Service s3Service;
	
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

	public int insertMessage(ChatMessageDTO messageDTO) {
		return cDao.insertMessage(messageDTO);
	}

	public void insertMsgImg(ChatMessageDTO messageDTO) throws IOException {
		//String filePath = "images/chat/";
		List<String> imgs = messageDTO.getImgs();
		
		for(String img : imgs) {
            messageDTO.setContent("[img_asdfzv] " + img);
    		cDao.insertChatMsg(messageDTO);
    		
    		int msgId=messageDTO.getMsgId();
    		
    		messageDTO.setMsgId(msgId);
    		messageDTO.setContent(img);
    		cDao.insertChatImg(messageDTO);
        }
	}
	
    //약속 잡기
	public void reserve(Map<String, Object> map) {
		cDao.updateReserve(map);
		cDao.insertDeal(map);
	}

	//약속 취소
	public void cancelReserve(Map<String, Object> map) {
		cDao.cancelReserve(map);
		cDao.deleteDeal(map);
	}
}
