package com.liveshop.liveservice.gateway.security;

import static org.springframework.util.StringUtils.hasText;

import com.liveshop.liveservice.gateway.security.model.Credentials;
import com.liveshop.liveservice.gateway.security.model.UserAuthentication;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityGateway {

  private static final String HEADER_AUTHORIZATION = "authorization";
  private static final String BEARER_AUTHORIZATION = "Bearer ";

  public UserAuthentication getUser() {
    UserAuthentication userPrincipal = null;
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Object principal = securityContext.getAuthentication().getPrincipal();
    if (principal instanceof UserAuthentication) {
      userPrincipal = ((UserAuthentication) principal);
    }
    return userPrincipal;
  }

  public Credentials getCredentials() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return (Credentials) securityContext.getAuthentication().getCredentials();
  }

  public String getBearerToken(HttpServletRequest request) {
    String bearerToken = null;
    String authorization = request.getHeader(HEADER_AUTHORIZATION);
    if (hasText(authorization) && authorization.startsWith(BEARER_AUTHORIZATION)) {
      bearerToken = authorization.substring(7);
    }
    return bearerToken;
  }
}