package com.shinhan.heehee.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminMainDao {
	
	@Autowired
	SqlSession sqlsession;
	
	String namespace = "com.shinhan.heehee.admin";
	
	public void main_page() {
		
	}
	
}


