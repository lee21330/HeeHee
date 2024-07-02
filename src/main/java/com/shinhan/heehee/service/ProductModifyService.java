package com.shinhan.heehee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.ProductModifyDAO;
import com.shinhan.heehee.dto.request.ProductDetailRequestDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;

@Service
public class ProductModifyService {

	@Autowired
	ProductModifyDAO productModifyDao;
	
	public ProdDetailDTO prodInfo(ProductDetailRequestDTO sampleDTO) {
		return productModifyDao.productInfo(sampleDTO);
	}
	
	public List<ProdDetailImgDTO> prodImg(Integer prodSeq) {
		return productModifyDao.productImg(prodSeq);
	}
	
	public List<ProductCategoryDTO> category() {
		return productModifyDao.category();
	}
	
	public List<ProductCategoryDTO> detailCategory(String category) {
		return productModifyDao.detailCategory(category);
	}
}
