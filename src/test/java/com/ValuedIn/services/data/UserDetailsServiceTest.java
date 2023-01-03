package com.ValuedIn.services.data;

import com.ValuedIn.repositories.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserDetailsServiceTest {

  @Mock
  private UserDetailsRepository userDetailsRepository;

  @Test
  public void constructorSmokeTest(){
    new UserDetailsService(userDetailsRepository);
  }

}