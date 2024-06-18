package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.AdminBanHistoryDAO;

@Service
public class AdminBanHistoryService {
	
	@Autowired
	AdminBanHistoryDAO banHistoryDAO;
	
	public void searchAll() {
		banHistoryDAO.searchAll();
	}

}
