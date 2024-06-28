package com.shinhan.heehee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.ChatMessageDTO;
import com.shinhan.heehee.dto.response.ChatRoomDTO;
import com.shinhan.heehee.dto.response.RoomDetailDTO;
import com.shinhan.heehee.dto.response.RoomMessageDTO;
import com.shinhan.heehee.dto.response.RoomProductDTO;
import com.shinhan.heehee.dto.response.RoomUserDTO;

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
		RoomUserDTO roomUserDTO = sqlSession.selectOne(namespace + "getRoomUser", map.get("loginUserId"));
		RoomDetailDTO roomDetailDTO = new RoomDetailDTO(roomProductDTO, roomUserDTO, roomMessageList);
		return roomDetailDTO;
	}

	public int updateReadCheck(Map<String, Object> map) {
		return sqlSession.update(namespace + "updateReadCheck", map);
	}
	
	public int updatePrice(Map<String, Object> map) {
		return sqlSession.update(namespace + "updatePrice", map);
	}
	
	public int updatePoint(Map<String, Object> map) {
		return sqlSession.update(namespace + "updatePoint", map);
	}

	public int insertMessage(ChatMessageDTO messageDTO) {
		return sqlSession.insert(namespace + "insertMessage", messageDTO);
	}

	public int insertChatMsg(ChatMessageDTO messageDTO) {
		return sqlSession.insert(namespace + "insertChatMsg", messageDTO);
		
	}

	public void insertChatImg(ChatMessageDTO messageDTO) {
		sqlSession.insert(namespace + "insertChatImg", messageDTO);
	}

	public void updateProStatus(Map<String, Object> map) {
		sqlSession.update(namespace + "updateProStatus", map.get("productSeq"));
	}

	public void insertDeal(Map<String, Object> map) {
		sqlSession.insert(namespace + "insertDeal", map);
	}

}
