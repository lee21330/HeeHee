package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.AlarmChatDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;

@Repository
public class AlarmDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.alarm.";
	
	// 알림 전체 조회
	public List<AlarmChatDTO> alarmList(String userId) {
		return sqlSession.selectList(namespace + "alarmList", userId);
	}

	// 미확인 알림 조회
	public List<AlarmChatDTO> alarmUnck(String userId) {
		return sqlSession.selectList(namespace + "alarmUnck", userId);
	}
	
}