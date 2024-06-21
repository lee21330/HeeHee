package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductDetailDAO;
import com.shinhan.heehee.dao.ProductModifyDAO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProdDetailRecoDTO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Service
public class ProductModifyService {

	@Autowired
	ProductModifyDAO productModifyDao;
	
	public ProdDetailDTO prodInfo(Integer prodSeq) {
		return productModifyDao.productInfo(prodSeq);
	}
	
	public List<ProdDetailImgDTO> prodImg(Integer prodSeq) {
		return productModifyDao.productImg(prodSeq);
	}
}
