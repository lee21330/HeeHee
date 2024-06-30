package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.response.UserDTO;


@Service
public class UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> signup(UserDTO userDto) {

        if(userDao.signup(userDto) > 0) {
            return ResponseEntity.ok("회원 가입에 성공했습니다!");
        } else {
        	return ResponseEntity.ok("회원 가입에 실패했습니다.");
        }
	}
	
	public UserDTO login(String userId, String userPw) {
		UserDTO user = userDao.findUserByUsername(userId);
		if(!passwordEncoder.matches(userPw, user.getPassword())) return null;
		return user;
	}
}
