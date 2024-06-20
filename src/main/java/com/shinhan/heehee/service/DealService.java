package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.DealDAO;

@Service
public class DealService {

	@Autowired
	DealDAO dealDao;
	
	public int deal() {
		return dealDao.deal();
	}
}
