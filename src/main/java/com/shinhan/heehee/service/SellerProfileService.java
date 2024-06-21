package com.shinhan.heehee.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.SellerProfileDAO;
import com.shinhan.heehee.dto.response.SellerProfileDTO;

@Service
public class SellerProfileService {

	@Autowired
	SellerProfileDAO SellerProfileDao;
	
	public SellerProfileDTO sellerinfo(String id) {
		return SellerProfileDao.sellerinfo(id);
	}

	public List<SellerProfileDTO> sellerprod(String id) {
		return SellerProfileDao.sellerprod(id);
	}
}
