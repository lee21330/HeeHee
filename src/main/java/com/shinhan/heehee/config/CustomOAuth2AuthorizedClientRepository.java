package com.shinhan.heehee.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

public class CustomOAuth2AuthorizedClientRepository implements OAuth2AuthorizedClientRepository{
	
	private final OAuth2AuthorizedClientService authorizedClientService;

	public CustomOAuth2AuthorizedClientRepository(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }
	
	@Override
	public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId,
			Authentication principal, HttpServletRequest request) {
		return authorizedClientService.loadAuthorizedClient(clientRegistrationId, principal.getName());
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal,
			HttpServletRequest request, HttpServletResponse response) {
		authorizedClientService.saveAuthorizedClient(authorizedClient, principal);
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, Authentication principal,
			HttpServletRequest request, HttpServletResponse response) {
		authorizedClientService.removeAuthorizedClient(clientRegistrationId, principal.getName());
	}

}
