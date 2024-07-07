package com.shinhan.heehee.dao;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.heehee.dto.request.BanUserDTO;
import com.shinhan.heehee.dto.response.UserDTO;

@Repository("userDao")
public class UserDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.heehee.user.";
	
	public UserDTO findUserByUsername(String username) {
		return sqlSession.selectOne(namespace + "findUserByUsername", username);
	}
	
	public Optional<UserDTO> findByUserId(Long userId) {
		return sqlSession.selectOne(namespace + "findByUserId", userId);
	}
	
	public UserDTO findByUserEmail(String email) {
		return sqlSession.selectOne(namespace + "findByUserEmail", email);
	}
	
	public int signup(UserDTO userDto) {
		return sqlSession.insert(namespace + "signup", userDto);
	}
	
	public Map<String,Object> findNickName(String userName) {
		return sqlSession.selectOne(namespace + "findNickName", userName);
	}
	
	public UserDTO getBuyerInfo(String buyerId) {
		return sqlSession.selectOne(namespace + "getBuyerInfo" ,buyerId);
	}
	
	public int dupIdCheck(String id) {
		return sqlSession.selectOne(namespace + "dupIdCheck", id);
	}
	
	public int dupNickCheck(String nickName) {
		return sqlSession.selectOne(namespace + "dupNickCheck", nickName);
	}
	
	public int dupEmailCheck(String email) {
		return sqlSession.selectOne(namespace + "dupEmailCheck", email);
	}

	public BanUserDTO banCheck(String userId) {
		return sqlSession.selectOne(namespace + "banCheck", userId);
	}
}