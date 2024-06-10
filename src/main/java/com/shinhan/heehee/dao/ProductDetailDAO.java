
package com.shinhan.heehee.dao;

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
}
