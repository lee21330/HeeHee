package com.shinhan.heehee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.Response;
import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.request.BanUserDTO;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.exception.UserNotFoundException;


@Service
public class UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> signup(UserDTO userDto) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++" + userDto);
		Map<String,Object> response = new HashMap<String,Object>();
		if (!userDto.getPassword().equals(null)) {
			// BCryptPasswordEncoder 생성
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(encodedPassword);
		}

        if(userDao.signup(userDto) > 0) {
        	response.put("success", true);
        	response.put("message", "회원 가입에 성공했습니다!");
            return ResponseEntity.ok(response);
        } else {
        	response.put("success", false);
        	response.put("message", "회원 가입에 실패했습니다.");
        	return ResponseEntity.ok(response);
        }
	}
	
	public UserDTO login(String userId, String userPw) {
		UserDTO user = userDao.findUserByUsername(userId);
		if(user == null || !passwordEncoder.matches(userPw, user.getPassword())) throw new UserNotFoundException("UserNotFound");
		return user;
	}
	
	public Map<String, Object> dupIdCheck(String id) {
		Map<String,Object> response = new HashMap<String,Object>();
		int result = userDao.dupIdCheck(id);
		if(result == 0) {
			response.put("able", true);
			response.put("message", "사용가능한 ID입니다.");
		} else {
			response.put("able", false);
			response.put("message", "중복된 ID가 존재합니다.");
		}
		return response;
	}
	
	public Map<String, Object> dupNickCheck(String nickName) {
		Map<String,Object> response = new HashMap<String,Object>();
		int result = userDao.dupNickCheck(nickName);
		if(result == 0) {
			response.put("able", true);
			response.put("message", "사용가능한 닉네임입니다.");
		} else {
			response.put("able", false);
			response.put("message", "중복된 닉네임이 존재합니다.");
		}
		return response;
	}
	
	public Map<String, Object> dupEmailCheck(String email) {
		Map<String,Object> response = new HashMap<String,Object>();
		int result = userDao.dupEmailCheck(email);
		if(result == 0) {
			response.put("able", true);
			response.put("message", "사용가능한 이메일입니다.");
		} else {
			response.put("able", false);
			response.put("message", "중복된 이메일이 존재합니다.");
		}
		return response;
	}
	
	public UserDTO findByUserEmail(String email) {
		return userDao.findByUserEmail(email);
	}
	
	public BanUserDTO banCheck(String userId) {
		return userDao.banCheck(userId);
	}
	
	public List<Map<String,Object>> getBankKind() {
		return userDao.getBankKind();
	}
}