package com.shinhan.heehee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	TestDAO testDao;
	
	public int test() {
		return testDao.test();
	}
	
	public List<Map<String,Object>> mapTest() {
		return testDao.mapTest();
	}
}
