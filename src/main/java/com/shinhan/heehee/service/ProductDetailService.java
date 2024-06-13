package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDao;
	
	public SellProDTO prodInfo(Integer prodSeq) {
		return productDetailDao.productInfo(prodSeq);
	}
	
	public List<SellProDTO> prodImg(Integer prodSeq) {
		return productDetailDao.productImg(prodSeq);
	}
}
