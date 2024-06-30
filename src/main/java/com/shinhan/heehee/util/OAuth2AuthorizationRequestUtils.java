package com.shinhan.heehee.util;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest.Builder;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponseType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

public class OAuth2AuthorizationRequestUtils {

    public static OAuth2AuthorizationRequest convert(HttpServletRequest request) {
        String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
        String redirectUri = request.getParameter(OAuth2ParameterNames.REDIRECT_URI);
        String state = request.getParameter(OAuth2ParameterNames.STATE);
        String scope = request.getParameter(OAuth2ParameterNames.SCOPE);
        String authorizationUri = request.getRequestURL().toString();

        Builder builder = OAuth2AuthorizationRequest.authorizationCode()
                .clientId(clientId)
                .authorizationUri(authorizationUri)
                .redirectUri(redirectUri)
                .state(state);

        if (scope != null && !scope.isEmpty()) {
            builder.scopes(Collections.singleton(scope));
        }

        return builder.build();
    }
}