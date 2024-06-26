package com.shinhan.heehee.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinhan.heehee.util.CookieUtil;
import com.shinhan.heehee.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler{

	@Autowired
	private final JwtUtil jwtUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = jwtUtil.generateToken(authentication.getName());
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

        // JSON 형태로 응답을 반환합니다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        response.getWriter().flush();
		
	}

}
