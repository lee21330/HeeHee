package com.shinhan.heehee.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.requset.AdminBanHistoryDTO;

@Repository
public class AdminBanHistoryDAO {
	
	@Autowired
	 SqlSession sqlsession;
	
	String namespace = "com.shinhan.heehee.dao.";
	
	//test
	public void searchAll() {
		System.out.println((AdminBanHistoryDTO)sqlsession.selectOne(namespace + "searchTest"));
	}
	
}