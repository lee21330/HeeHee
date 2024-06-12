package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ChattingDAO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;

@Service
public class ChattingService {
	
	@Autowired
	private ChattingDAO cDao;
	
	public List<ChatRoomDTO> getRoomList(String userId){
		return cDao.getRoomList(userId);
	}
}
