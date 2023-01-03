package com.ValuedIn.controllers;


import com.ValuedIn.services.data.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserControllerTest {

  @Mock
  private UserService userService;

  @Test
  public void constructorSmokeTest(){
    new UserController(userService);
  }

}