package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.ChatRoomDTO;

@Repository
public class ChattingDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.chatting.";

	public List<ChatRoomDTO> getRoomList(String userId) {
		return sqlSession.selectList(namespace + "getRoomList", userId);
	}

}
