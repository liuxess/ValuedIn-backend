package com.ValuedIn.config;

import com.ValuedIn.services.AuthenticationService;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;


public class AuthorizationFilter extends BasicAuthenticationFilter {

  private final AuthenticationService authenticationService;
  private static final String TOKEN_PREFIX = "Bearer ";
  public AuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService){
    super(authenticationManager);
    this.authenticationService = authenticationService;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");

    if(isAuthorizationTokenValid(authorizationHeader)){
      DecodedJWT decodedJWT = authenticationService.decodeToken(authorizationHeader.replace(TOKEN_PREFIX, ""));
      Claim login = decodedJWT.getClaim("login");
      authenticationService.updateSecurityContextForLogin(login.asString());
    }

    chain.doFilter(request, response);
  }

  private boolean isAuthorizationTokenValid(String authorizationToken){
    if(authorizationToken == null) return false;
    if(authorizationToken.isBlank()) return false;

    return authorizationToken.startsWith(TOKEN_PREFIX);
  }

}
