package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dao.TestDAO;

@Service
public class ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDao;
	
	public int test() {
		return productDetailDao.test();
	}
}
