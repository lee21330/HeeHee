package com.shinhan.heehee.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.shinhan.heehee.util.OAuth2AuthorizationRequestUtils;

public class CustomOAuth2LoginAuthenticationFilter extends OAuth2LoginAuthenticationFilter {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;

    public CustomOAuth2LoginAuthenticationFilter(CustomClientRegistrationRepository clientRegistrationRepository,
    		CustomOAuth2AuthorizedClientRepository authorizedClientRepository, AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(clientRegistrationRepository, authorizedClientRepository, "/oauth2/authorization/*");
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientRepository = authorizedClientRepository;
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	System.out.println("registrationId: " + request.getParameter("registrationId"));
        super.doFilter(request, response, chain);
    }

	/*
	 * @Override public Authentication attemptAuthentication(HttpServletRequest
	 * request, HttpServletResponse response) throws AuthenticationException {
	 * OAuth2AuthorizationRequest authorizationRequest =
	 * OAuth2AuthorizationRequestUtils.convert(request);
	 * 
	 * String registrationId = request.getParameter("registrationId");
	 * System.out.println("attemptAuthentication: " + registrationId);
	 * ClientRegistration clientRegistration =
	 * clientRegistrationRepository.findByRegistrationId(registrationId);
	 * System.out.println(clientRegistration);
	 * 
	 * String authorizationCode = request.getParameter(OAuth2ParameterNames.CODE);
	 * OAuth2AuthorizationResponse authorizationResponse =
	 * OAuth2AuthorizationResponse .success(authorizationCode)
	 * .redirectUri(authorizationRequest.getRedirectUri())
	 * .state(authorizationRequest.getState()) .build();
	 * 
	 * OAuth2AuthorizationExchange authorizationExchange = new
	 * OAuth2AuthorizationExchange(authorizationRequest, authorizationResponse);
	 * 
	 * OAuth2LoginAuthenticationToken authenticationRequest = new
	 * OAuth2LoginAuthenticationToken( clientRegistration, authorizationExchange);
	 * 
	 * return this.getAuthenticationManager().authenticate(authenticationRequest); }
	 */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        logger.warn("OAuth2 인증 실패: " + failed.getMessage());
        logger.warn("OAuth2 인증 실패: " + failed.getLocalizedMessage());
        logger.warn("OAuth2 인증 실패: " + failed.getCause());
        logger.warn("OAuth2 인증 실패: " + failed.fillInStackTrace());
        super.unsuccessfulAuthentication(request, response, failed);
    }
}