package com.shinhan.heehee.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class CustomClientRegistrationRepository implements ClientRegistrationRepository {
	
	private final Map<String, ClientRegistration> registrations = new HashMap<>();
	
	 

	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		return registrations.get(registrationId);
	}

	public CustomClientRegistrationRepository() {
        // 여기에 클라이언트 등록 정보를 추가합니다.
        registrations.put("google", googleClientRegistration());
        registrations.put("kakao", kakaoClientRegistration());
        registrations.put("naver", naverClientRegistration());
    }
	
	private ClientRegistration googleClientRegistration() {
		return ClientRegistration.withRegistrationId("google")
				.clientId("965074068088-1a323p3et6ropa92pdccrvs41u7opq1m.apps.googleusercontent.com")
				.clientSecret("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")
				.scope("openid", "profile", "email")
				.authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .userNameAttributeName("sub")
                .clientName("HeeHee")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
	}

	private ClientRegistration kakaoClientRegistration() {
		return ClientRegistration.withRegistrationId("kakao")
				.clientId("856ecd87a235038c7924d9f15ca3512d")
                .clientSecret("ee7w410lgopzbryeHsgeOOjNJGVgG1EH")
                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("profile_nickname", "account_email")
                .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                .tokenUri("https://kauth.kakao.com/oauth/token")
                .userInfoUri("https://kapi.kakao.com/v2/user/me")
                .userNameAttributeName("id")
                .clientName("Kakao")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
	}
	
	private ClientRegistration naverClientRegistration() {
		return ClientRegistration.withRegistrationId("naver")
				.clientId("B0CVG6nM6DYl3XOJlY3i")
				.clientSecret("IWltw8IhZY")
				.clientAuthenticationMethod(ClientAuthenticationMethod.POST)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.scope("name", "email", "phoneNum")
				.authorizationUri("https://nid.naver.com/oauth2.0/authorize")
	            .tokenUri("https://nid.naver.com/oauth2.0/token")
	            .userInfoUri("https://openapi.naver.com/v1/nid/me")
	            .userNameAttributeName("response")
	            .clientName("Naver")
	            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
	            .build();
	}
}
