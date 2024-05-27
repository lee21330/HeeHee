package com.shinhan.heehee.service;

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
}
