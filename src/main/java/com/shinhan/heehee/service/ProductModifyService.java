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
	
	/* 이미지파일 삽입, 카테고리 삽입, 제품명 삽입, 판매제품 테이블 삽입은 ProductDetailService에 있음 */
	
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
