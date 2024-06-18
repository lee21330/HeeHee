package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.SellProDTO;

@Repository
public class MyPageDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.myPage.";
	public List<SellProDTO> saleList(String userId) {
		return sqlSession.selectList(namespace + "saleList", userId);
	}

}
