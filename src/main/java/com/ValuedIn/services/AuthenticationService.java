package com.ValuedIn.services;

import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.models.dto.responses.AuthResponse;
import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.models.entities.AuthenticationRequest;
import com.ValuedIn.models.entities.UserCredentials;
import com.ValuedIn.services.data.UserCredentialService;
import com.ValuedIn.services.data.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthenticationService {

  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private final UserCredentialService userCredentialService;
  private final UserService userService;
  private final int expirationInMinutes;
  private final String issuer;
  private final JWTVerifier verifier;
  private final Algorithm algorithm;



  public AuthenticationService(
      @Value("${auth.secret}") String secret,
      @Value("${auth.expirationInMinutes}") int expirationInMinutes,
      @Value("${auth.issuer}") String issuer,
      UserService userService,
      UserCredentialService userCredentialService){
    this.userCredentialService = userCredentialService;
    this.userService = userService;
    this.expirationInMinutes = expirationInMinutes;
    this.issuer = issuer;
    this.algorithm = Algorithm.HMAC512(secret);
    this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
  }

  public void register(NewUser newUser){
      newUser.setPassword(PASSWORD_ENCODER.encode(newUser.getPassword()));
      userService.saveNewUser(newUser);
  }

  public AuthResponse authenticate(AuthenticationRequest authenticationRequest){
    UserCredentials userCredentials = tryAuthenticate(authenticationRequest);
    UserInfo userInfo = updateSecurityContextForLogin(userCredentials);
    return new AuthResponse(createNewJWT(userCredentials.getLogin()), userInfo.getRole()) ;
  }

  public AuthResponse reAuthenticate(){
    UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String newJWT = createNewJWT(userInfo.getLogin());
    return new AuthResponse(newJWT, userInfo.getRole());
  }

  @SneakyThrows
  private UserCredentials tryAuthenticate(AuthenticationRequest authenticationRequest){
    UserCredentials userCredentials =
        userCredentialService.getCredentialsFromLogin(authenticationRequest.getLogin());

    if(userCredentials != null){
      if(userCredentials.isExpired())
        throw new HttpSessionRequiredException("User is expired");


      if(PASSWORD_ENCODER.matches(authenticationRequest.getPassword(), userCredentials.getPassword()))
        return userCredentials;
    }

    throw new HttpSessionRequiredException("Incorrect login or password");
  }

  private String createNewJWT(String login){
    return JWT.create()
        .withClaim("login", login)
        .withIssuer(issuer)
        .withExpiresAt(getExpirationTime())
        .sign(algorithm);
  }

  private Instant getExpirationTime(){
    LocalDateTime expirationDate = LocalDateTime.now().plus(expirationInMinutes, ChronoUnit.MINUTES);
    return expirationDate.atZone(ZoneId.systemDefault()).toInstant();
  }

  public void updateSecurityContextForLogin(String login){
    UserCredentials userCredentials = userCredentialService.getCredentialsFromLogin(login);
    updateSecurityContextForLogin(userCredentials);
  }

  private UserInfo updateSecurityContextForLogin(UserCredentials userCredentials){
    UserInfo userInfo = userService.getUserInfo(userCredentials.getLogin())
        .orElseThrow(() -> new AuthenticationServiceException("User is setup incorrectly"));
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo, userCredentials, null); //TODO: use roles for actual authorities?
    SecurityContextHolder.getContext().setAuthentication(token);
    userCredentialService.updateLastActive(userCredentials);
    return userInfo;
  }

  public DecodedJWT decodeToken(String token) {
    return verifier.verify(token);
  }
}
