
package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.ProductDetailRequestDTO;
import com.shinhan.heehee.dto.response.ProdDetailDTO;
import com.shinhan.heehee.dto.response.ProdDetailImgDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;

@Repository
public class ProductModifyDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.productDetail.";
	
	public ProdDetailDTO productInfo(ProductDetailRequestDTO sampleDTO) {
		return sqlSession.selectOne(namespace + "productInfo", sampleDTO);
	}
	
	public List<ProdDetailImgDTO> productImg(Integer prodSeq) {
		return sqlSession.selectList(namespace + "productImg", prodSeq);
	}
	
	public List<ProductCategoryDTO> category() {
		return sqlSession.selectList(namespace + "getCategory");
	}
	
	public List<ProductCategoryDTO> detailCategory(String category) {
		return sqlSession.selectList(namespace + "getDetailCategory", category);
	}
}
