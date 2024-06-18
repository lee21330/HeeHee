package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.SellProDTO;

@Repository
public class SaleListDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.saleList.";
	public List<SellProDTO> saleList(String userId) {
		return sqlSession.selectList(namespace + "saleList", userId);
	}
	public List<SellProDTO> productImg(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + "productImg", userId);
	}
	
}
