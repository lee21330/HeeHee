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
		String filePath = "images/chat/";
		List<MultipartFile> files = messageDTO.getImgs();
		
		for(MultipartFile file : files) {
            String fileName = s3Service.uploadOneObject(file, filePath);
            messageDTO.setContent("[img_asdfzv] " + fileName);
    		cDao.insertChatMsg(messageDTO);
    		
    		int msgId=messageDTO.getMsgId();
    		
    		messageDTO.setMsgId(msgId);
    		messageDTO.setContent("chat/" + fileName);
    		cDao.insertChatImg(messageDTO);
        }
	}

	public void reserve(Map<String, Object> map) {
		cDao.updateProStatus(map);
		cDao.insertDeal(map);
	}
}
