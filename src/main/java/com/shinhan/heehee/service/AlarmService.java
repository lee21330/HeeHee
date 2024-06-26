package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AlarmDAO;
import com.shinhan.heehee.dto.response.AlarmChatDTO;
import com.shinhan.heehee.dto.response.AlarmDTO;

@Service
public class AlarmService {

	@Autowired
	AlarmDAO alarmDAO;
	
	// 알림 전체 조회
	public List<AlarmChatDTO> alarmList(String userId) {
		return alarmDAO.alarmList(userId);
	}

	// 미확인 알림 조회
	public List<AlarmChatDTO> alarmUnck(String userId) {
		return alarmDAO.alarmUnck(userId);
	}

	// 알림 확인
	public int alarmUpdate(int alNum) {
		return alarmDAO.alarmUpdate(alNum);
	}
	
}