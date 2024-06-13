
package com.shinhan.heehee.dao;

import java.util.List;
import java.util.Map;

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
	
	public List<Map<String,Object>> mapTest() {
		return sqlSession.selectList(namespace + "mapTest");
	}
}
