package com.shinhan.heehee.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

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
			handleGoogleLogin(oauth2User, response);
			break;
		case "naver":
			handleNaverLogin(oauth2User, response);
			break;
		case "kakao":
			handleKakaoLogin(oauth2User, response);
			break;
		}

		response.sendRedirect("/heehee/main");
	}
	
	private void handleGoogleLogin(DefaultOAuth2User oauth2User, HttpServletResponse response) {
        // 구글 로그인 관련 처리
        System.out.println("Logged in with Google");
    }

    private void handleNaverLogin(DefaultOAuth2User oauth2User, HttpServletResponse response) {
        // 네이버 로그인 관련 처리
        System.out.println("Logged in with Naver");
    }
    
    private void handleKakaoLogin(DefaultOAuth2User oauth2User, HttpServletResponse response) {
        // 카카오 로그인 관련 처리
        System.out.println("Logged in with KaKao");
    }

}
