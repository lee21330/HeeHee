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
	
	public List<AlarmChatDTO> alarmList() {
		return alarmDAO.alarmList();
	}
	
}