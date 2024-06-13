
package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.SellProDTO;

@Repository
public class ProductDetailDAO {
	/*
	 * @Autowired SqlSession sqlSession;
	 * 
	 * String namespace = "com.shinhan.test.";
	 */
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.productDetail.";
	
	public SellProDTO productInfo(Integer prodSeq) {
		return sqlSession.selectOne(namespace + "productInfo", prodSeq);
	}
	
	public List<SellProDTO> productImg(Integer prodSeq) {
		return sqlSession.selectList(namespace + "productImg", prodSeq);
	}
}
