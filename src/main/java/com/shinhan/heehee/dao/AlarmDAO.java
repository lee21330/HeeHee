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
	
	public List<AlarmChatDTO> alarmList() {
		return sqlSession.selectList(namespace + "alarmList");
	}
	
}