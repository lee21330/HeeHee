package com.shinhan.heehee.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shinhan.heehee.dao.UserDAO;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.exception.UserNotFoundException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	UserDTO result = userDao.findUserByUsername(userId);
    	if(result != null) result = addAuthorities(result);
    	return result;
    }
    
    public Map<String,Object> findNickName(String userName) {
    	return userDao.findNickName(userName);
    }

    private UserDTO addAuthorities(UserDTO userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));
        return userDto;
    }
}