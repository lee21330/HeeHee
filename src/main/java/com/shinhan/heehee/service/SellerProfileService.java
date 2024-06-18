package com.shinhan.heehee.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.SellerProfileDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class SellerProfileService {

	@Autowired
	SellerProfileDAO SellerProfileDao;
	
	public SellProDTO sellerinfo(String id) {
		return SellerProfileDao.sellerinfo(id);
	}
}
