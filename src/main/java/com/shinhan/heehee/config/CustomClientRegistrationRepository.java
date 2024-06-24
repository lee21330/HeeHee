package com.shinhan.heehee.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

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
    }
	
	private ClientRegistration googleClientRegistration() {
		return ClientRegistration.withRegistrationId("google")
				.clientId("965074068088-1a323p3et6ropa92pdccrvs41u7opq1m.apps.googleusercontent.com")
				.clientSecret("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")
				.scope("openid", "profile", "email")
				.authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName("sub")
                .clientName("HeeHee")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
	}
}
