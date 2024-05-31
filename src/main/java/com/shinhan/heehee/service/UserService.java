package com.shinhan.heehee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.response.UserDTO;


@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	
	public ResponseEntity<?> signup(UserDTO userDto) {

        if(userDao.signup(userDto) > 0) {
            return ResponseEntity.ok("회원 가입에 성공했습니다!");
        } else {
        	return ResponseEntity.ok("회원 가입에 실패했습니다.");
        }
	}
}
