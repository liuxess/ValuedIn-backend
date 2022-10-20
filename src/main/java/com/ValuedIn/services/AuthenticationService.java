package com.ValuedIn.services;


import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  public String encodePassword(String password){
    return password.replaceAll(".", "##");
  }

}
