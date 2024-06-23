package com.shinhan.heehee.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

	private final Map<String, OAuth2AuthorizedClient> authorizedClients = new HashMap<>();

	@Override
	public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId,
			String principalName) {
		return (T) authorizedClients.get(principalName + "-" + clientRegistrationId);
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		String key = principal.getName() + "-" + authorizedClient.getClientRegistration().getRegistrationId();
		authorizedClients.put(key, authorizedClient);
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		String key = principalName + "-" + clientRegistrationId;
		authorizedClients.remove(key);
	}
}
