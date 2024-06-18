package com.shinhan.heehee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.MessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;
import com.shinhan.heehee.dto.response.RoomMessageDTO;
import com.shinhan.heehee.dto.response.RoomProductDTO;

@Repository
public class ChattingDAO {

	@Autowired
	SqlSession sqlSession;

	String namespace = "com.shinhan.chatting.";

	public List<ChatRoomDTO> getRoomList(String userId) {
		return sqlSession.selectList(namespace + "getRoomList", userId);
	}

	public RoomDetailDTO getRoomDetail(Map<String, Object> map) {
		RoomProductDTO roomProductDTO = sqlSession.selectOne(namespace + "getRoomProduct", map);
		List<RoomMessageDTO> roomMessageList = sqlSession.selectList(namespace + "getRoomMessage", map.get("chatRoomId"));
		RoomDetailDTO roomDetailDTO = new RoomDetailDTO(roomProductDTO, roomMessageList);
		return roomDetailDTO;
	}

	public int updateReadCheck(Map<String, Object> map) {
		return sqlSession.update(namespace + "updateReadCheck", map);
	}

	public int insertMessage(MessageDTO messageDTO) {
		return sqlSession.insert(namespace + "insertMessage", messageDTO);
	}

	public int insertChatMsg(MessageDTO messageDTO) {
		return sqlSession.insert(namespace + "insertChatMsg", messageDTO);
		
	}

	public void insertChatImg(MessageDTO messageDTO) {
		sqlSession.insert(namespace + "insertChatImg", messageDTO);
	}

}
