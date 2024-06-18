package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class ProductDetailService {

	ProductDetailDAO productDetailDao;
	
	public SellProDTO prodInfo(Integer prodSeq) {
		return productDetailDao.productInfo(prodSeq);
	}
	
	public List<SellProDTO> prodImg(Integer prodSeq) {
		return productDetailDao.productImg(prodSeq);
	}
	
	public SellProDTO userIntroduce(Integer prodSeq) {
		return productDetailDao.userIntroduce(prodSeq);
	}

	public List<SellProDTO> prodReco() {
		return productDetailDao.prodReco();
	}
	
	/*
	 * public SellProDTO viewLog(Integer prodSeq) { return
	 * productDetailDao.viewLog(prodSeq); }
	 */
}
