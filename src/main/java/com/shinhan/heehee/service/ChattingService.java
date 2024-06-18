package com.shinhan.heehee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.shinhan.heehee.dao.ChattingDAO;
import com.shinhan.heehee.dto.request.MessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;

@Service
@Transactional
public class ChattingService {
	
	@Autowired
	private ChattingDAO cDao;
	
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

	public int insertMessage(MessageDTO messageDTO) {
		return cDao.insertMessage(messageDTO);
	}

	public void insertMsgImg(MessageDTO messageDTO, MultipartFile img) {
		String imgName=img.getOriginalFilename();
		messageDTO.setContent("[img_asdfzv] " + imgName);
		cDao.insertChatMsg(messageDTO);
		
		int msgId=messageDTO.getMsgId();
		
		messageDTO.setMsgId(msgId);
		messageDTO.setContent("chat/" + imgName);
		cDao.insertChatImg(messageDTO);
	}
}
