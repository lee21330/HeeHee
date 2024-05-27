package com.shinhan.heehee.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.test.";
	
	public int test() {
		return sqlSession.selectOne(namespace + "returnNum");
	}
}
