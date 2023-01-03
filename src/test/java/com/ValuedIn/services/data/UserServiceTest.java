package com.ValuedIn.services.data;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceTest {

  @Mock
  private UserCredentialService userCredentialService;

  @Mock
  private UserDetailsService userDetailsService;

  @Test
  public void constructorSmokeTest(){
    new UserService(userCredentialService, userDetailsService);
  }

}