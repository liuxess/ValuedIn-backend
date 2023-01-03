package com.ValuedIn.services;


import com.ValuedIn.services.data.UserCredentialService;
import com.ValuedIn.services.data.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class AuthenticationServiceTest {


  @Mock
  private UserCredentialService userCredentialService;

  @Mock
  private UserService userService;

  private static final String TEST_SECRET = "secret";
  private static final String TEST_ISSUER = "issuer";
  private static final int TEST_EXPIRATION = 10;

  @Test
  public void constructorSmokeTest(){
    new AuthenticationService(TEST_SECRET, TEST_EXPIRATION, TEST_ISSUER, userService, userCredentialService);
  }
}