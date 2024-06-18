package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.SaleListDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class SaleListService {

	@Autowired
	SaleListDAO saleListDao;
	
	public List<SellProDTO> saleList(String userId) {
		return saleListDao.saleList(userId);
	}
	public List<SellProDTO> prodImg(String userId) {
		return saleListDao.productImg(userId);
	}
}
