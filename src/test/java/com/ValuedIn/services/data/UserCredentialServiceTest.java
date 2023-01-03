package com.ValuedIn.services.data;
import com.ValuedIn.repositories.UsersCredentialsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserCredentialServiceTest {

  @Mock
  private UsersCredentialsRepository usersCredentialsRepository;

  @Test
  public void constructorSmokeTest(){
    new UserCredentialService(usersCredentialsRepository);
  }

}