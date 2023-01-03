package com.ValuedIn.models.entities;

import com.ValuedIn.enumerators.UserRole;
import org.junit.jupiter.api.Test;

class UserDetailsTest {

  private static final String TEST_LOGIN = "login";
  private static final UserRole TEST_ROLE = UserRole.DEFAULT;
  private static final String TEST_FIRST_NAME = "FirstName";
  private static final String TEST_LAST_NAME = "LastName";
  private static final String TEST_EMAIL = "email@email.com";
  private static final String TEST_PHONE = "PhoneNo";


  @Test
  public void constructorSmokeTest(){
    new UserDetails();
    new UserDetails(TEST_LOGIN, TEST_ROLE, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PHONE);
  }

}