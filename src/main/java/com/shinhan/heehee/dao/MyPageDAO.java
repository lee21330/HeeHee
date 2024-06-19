package com.shinhan.heehee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.JjimDTO;
import com.shinhan.heehee.dto.response.MyPageHeaderDTO;
import com.shinhan.heehee.dto.response.PurchaseListDTO;
import com.shinhan.heehee.dto.response.SaleListDTO;

@Repository
public class MyPageDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.myPage.";
	public List<SaleListDTO> saleList(String userId) {
		return sqlSession.selectList(namespace + "saleList", userId);
	}

	public List<PurchaseListDTO> purchaseList(String userId) {
		return sqlSession.selectList(namespace + "purchaseList", userId);
	}

	public List<JjimDTO> jjimList(String userId) {
		return sqlSession.selectList(namespace + "jjimList", userId);
	}

	public MyPageHeaderDTO sellerInfo(String userId) {
		return sqlSession.selectOne(namespace + "sellerInfo", userId);
	}

}
