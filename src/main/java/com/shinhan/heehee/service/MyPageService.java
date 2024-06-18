package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.MyPageDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class MyPageService {

	@Autowired
	MyPageDAO mypageDao;
	
	public List<SellProDTO> saleList(String userId) {
		return mypageDao.saleList(userId);
	}
}
