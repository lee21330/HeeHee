package com.shinhan.heehee.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.pay.PayRequestDTO;

@Repository
public class PayDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.pay.";
	
	public void insertPay(PayRequestDTO payDto) {
		sqlSession.insert(namespace + "insertPay", payDto);
	}
	
	public int completePay(int paySeq) {
		return sqlSession.update(namespace + "completePay", paySeq);
	}
}
