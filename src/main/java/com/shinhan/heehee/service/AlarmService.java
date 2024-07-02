package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	// 알림 생성 (알림 insert 해야 하는 각각의 service 파일에 생성해야 함)
	@Transactional
	public int alarmInsert(AlarmDTO alarm) {
		return alarmDAO.alarmInsert(alarm);
	}
	
	// 미확인 알림 개수
	public int alarmCount(String userId) {
		return alarmDAO.alarmCount(userId);
	}
}