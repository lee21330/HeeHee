package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.ProductModifyRequestDTO;
import com.shinhan.heehee.dto.response.CategoryDTO;
import com.shinhan.heehee.dto.response.MainProdRankDTO;
import com.shinhan.heehee.dto.response.MainProdRecentlyDTO;
import com.shinhan.heehee.dto.response.MainProdRecoDTO;
import com.shinhan.heehee.dto.response.ProductCategoryDTO;
import com.shinhan.heehee.dto.response.SellProDTO;

@Repository
public class MainDAO {
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.main.";
	String prodDetailNamespace = "com.shinhan.productDetail.";
	
	public SellProDTO productInfo(Integer prodSeq) {
		return sqlSession.selectOne(namespace + "productInfo", prodSeq);
	}
	
	public List<SellProDTO> productImg(Integer prodSeq) {
		return sqlSession.selectList(namespace + "productImg", prodSeq);
	}
	
	public SellProDTO userIntroduce(Integer prodSeq) {
		return sqlSession.selectOne(namespace + "userIntroduce", prodSeq);
	}
	
	public List<MainProdRankDTO> rankProdList() {
		return sqlSession.selectList(prodDetailNamespace + "prodRank");
	}

	public List<MainProdRecoDTO> recommandList(String loginId) {
		return sqlSession.selectList(prodDetailNamespace + "prodreco", loginId);
	}

	public List<MainProdRecentlyDTO> recentprodList() {
		return sqlSession.selectList(prodDetailNamespace + "recently");
	}

	public List<ProductCategoryDTO> cateList() {
		return sqlSession.selectList(prodDetailNamespace + "cateList");
	}
	
	public List<CategoryDTO> mainCateList() {
		return sqlSession.selectList(prodDetailNamespace + "mainCateList");
	}
	
}