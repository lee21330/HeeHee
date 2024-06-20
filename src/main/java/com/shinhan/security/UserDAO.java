package com.shinhan.security;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.UserDTO;

@Repository("userDao")
public class UserDAO {

	Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.heehee.user.";
	
	public UserDTO findUserByUsername(String username) {
		return sqlSession.selectOne(namespace + "findUserByUsername", username);
	}
	
	public Optional<UserDTO> findByUserId(Long userId) {
		return sqlSession.selectOne(namespace + "findByUserId", userId);
	}
	
	public int signup(UserDTO userDto) {
		return sqlSession.insert(namespace + "signup", userDto);
	}
}
