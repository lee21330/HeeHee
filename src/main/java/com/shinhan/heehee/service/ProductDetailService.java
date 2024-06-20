package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;

@Service
public class ProductDetailService {

	@Autowired
	ProductDetailDAO productDetailDao;
	
	public ProdDetailDTO prodInfo(Integer prodSeq) {
		return productDetailDao.productInfo(prodSeq);
	}
	
	public List<ProdDetailImgDTO> prodImg(Integer prodSeq) {
		return productDetailDao.productImg(prodSeq);
	}
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * productDetailDao.userIntroduce(prodSeq); }
	 */
	
	/*
	 * public SellProDTO userIntroduce(Integer prodSeq) { return
	 * productDetailDao.userIntroduce(prodSeq); }
	 */

	public List<ProdDetailRecoDTO> prodReco() {
		return productDetailDao.prodReco();
	}
}
