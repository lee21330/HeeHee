package com.shinhan.heehee.dao;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.response.UserDTO;

@Repository
public class UserDAO {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.heehee.user.";
	
	public Optional<UserDTO> findUserByUsername(String username) {
		return sqlSession.selectOne(namespace + "findUserByUsername", username);
	}
	
	public Optional<UserDTO> findByUserId(Long userId) {
		return sqlSession.selectOne(namespace + "findByUserId", userId);
	}
	
	public void save(UserDTO userDto) {
		sqlSession.insert(namespace + "save", userDto);
	}
}
