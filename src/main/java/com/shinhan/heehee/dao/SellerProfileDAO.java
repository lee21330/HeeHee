package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.SellerProfileDTO;

@Repository
public class SellerProfileDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.productDetail.";
	
	public SellerProfileDTO sellerinfo(String id) {
		return sqlSession.selectOne(namespace + "sellerInfo", id);
	}

	public List<SellerProfileDTO> sellerprod(String id) {
		return sqlSession.selectList(namespace + "sellerprod", id);
	}

	public SellerProfileDTO dealComplete(String id) {
		return sqlSession.selectOne(namespace + "dealComplete", id);
	}
	
}
