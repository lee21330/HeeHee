package com.shinhan.heehee.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.SellProDTO;

@Repository
public class SellerProfileDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.sellerProfile.";
	
	public SellProDTO sellerinfo(String id) {
		return sqlSession.selectOne(namespace + "sellerInfo", id);
	}
	
}
