package com.shinhan.heehee.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.heehee.config.AuthenticationFailure;
import com.shinhan.heehee.config.AuthenticationSuccess;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.exception.UserNotFoundException;
import com.shinhan.heehee.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
	
	@Autowired
	AuthenticationSuccess success;
	
	@Autowired
	AuthenticationFailure failure;
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> singUp(UserDTO userDto, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        if(!userDto.getPassword().equals(null)) {
            // BCryptPasswordEncoder 생성
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);
        }
        return userService.signup(userDto);
	}
	
	@PostMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException, ServletException {
		String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        
        UserDTO user = userService.login(userId, userPw);
        
        if(user == null) throw new UserNotFoundException();
        
        try {
            // 사용자 인증을 위한 UsernamePasswordAuthenticationToken 객체 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, userPw);
            // AuthenticationManager를 사용하여 인증 수행
            Authentication authentication = authenticationManager.authenticate(token);
            // 인증 성공 후 SecurityContext에 인증 객체 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            success.onAuthenticationSuccess(request, response, authentication);
            
        } catch (Exception e) {
        	failure.onAuthenticationFailure(request, response, null);
            redirectAttributes.addAttribute("error", true);
        }
    }
	}
	
