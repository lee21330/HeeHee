package com.shinhan.heehee.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinhan.heehee.dto.response.UserDTO;
import com.shinhan.heehee.service.UserService;
import com.shinhan.heehee.util.CookieUtil;
import com.shinhan.heehee.util.JwtUtil;

public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	AuthenticationSuccess success;

	@Autowired
	AuthenticationFailure failure;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// Authentication 객체에서 OAuth2AuthenticationToken 추출
		OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;

		// DefaultOAuth2User 객체를 통해 사용자 정보 추출
		DefaultOAuth2User oauth2User = (DefaultOAuth2User) oauth2Token.getPrincipal();

		// 사용자 속성 정보 출력
		oauth2User.getAttributes().forEach((key, value) -> {
			logger.info(key + ": " + value);
		});

		// 사용자 이름 출력
		String username = oauth2User.getName();
		logger.info("Username: " + username);

		// clientName 추출
		String clientName = oauth2Token.getAuthorizedClientRegistrationId();
		logger.info("clientName: " + clientName);

		switch (clientName) {
		case "google":
			handleGoogleLogin(oauth2User, request, response);
			break;
		case "naver":
			handleNaverLogin(oauth2User, request, response);
			break;
		case "kakao":
			handleKakaoLogin(oauth2User, request, response);
			break;
		}
	}
	
	private void handleGoogleLogin(DefaultOAuth2User oauth2User, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = oauth2User.getAttribute("name");
		String email = oauth2User.getAttribute("email");
		
		UserDTO userDto = userService.findByUserEmail(email);
		
		if(userDto != null) {
			authenticationHandle(request, response, userDto.getUsername(), userDto.getPassword());
		} else {
			String encodedName = URLEncoder.encode(name, "UTF-8");
	        String encodedEmail = URLEncoder.encode(email, "UTF-8");
			response.sendRedirect("/heehee/main?status=signup&name=" + encodedName + "&email=" + encodedEmail);
		}
		
        System.out.println("Logged in with Google");
    }

    private void handleNaverLogin(DefaultOAuth2User oauth2User, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Map<String,Object> naverRes =  oauth2User.getAttribute("response");
    	String email = (String) naverRes.get("email");
    	String name = (String) naverRes.get("name");
    	
    	UserDTO userDto = userService.findByUserEmail(email);
    	
    	if(userDto != null) {
			authenticationHandle(request, response, userDto.getUsername(), userDto.getPassword());
		} else {
			String encodedName = URLEncoder.encode(name, "UTF-8");
	        String encodedEmail = URLEncoder.encode(email, "UTF-8");
			response.sendRedirect("/heehee/main?status=signup&name=" + encodedName + "&email=" + encodedEmail);
		}
        System.out.println("Logged in with Naver");
    }
    
    private void handleKakaoLogin(DefaultOAuth2User oauth2User, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Map<String,Object> kakao_account = oauth2User.getAttribute("kakao_account");
    	Map<String,Object> profile = (Map<String, Object>) kakao_account.get("profile");
    	
    	String email = (String) kakao_account.get("email");
    	String nickName = (String) profile.get("nickname");
    	
    	UserDTO userDto = userService.findByUserEmail(email);
    	
    	if(userDto != null) {
			authenticationHandle(request, response, userDto.getUsername(), userDto.getPassword());
		} else {
			String encodedNickName = URLEncoder.encode(nickName, "UTF-8");
	        String encodedEmail = URLEncoder.encode(email, "UTF-8");
			response.sendRedirect("/heehee/main?status=signup&nickName=" + encodedNickName + "&email=" + encodedEmail);
		}
        System.out.println("Logged in with KaKao");
    }
    
    
    private void authenticationHandle(HttpServletRequest request, HttpServletResponse response, 
    							String userId, String userPw) throws IOException, ServletException {
    	String token = jwtUtil.generateToken(userId);
		token = String.format("Bearer %s", token);
		
		Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", "success");
        responseBody.put("message", "인증 성공");
        responseBody.put("token", token);

        // JWT 토큰을 Response Header에 설정
        response.addHeader("Authorization", token);
        CookieUtil.addCookie("Authorization", token, response);
        
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        response.sendRedirect("/heehee/main");
    }

}
