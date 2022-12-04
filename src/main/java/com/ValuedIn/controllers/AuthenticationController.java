package com.ValuedIn.controllers;

import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.models.dto.responses.AuthResponse;
import com.ValuedIn.models.entities.AuthenticationRequest;
import com.ValuedIn.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public void createUser(@RequestBody NewUser newUser){
    authenticationService.register(newUser);
  }

  @PostMapping("/login")
  public AuthResponse logIn(@RequestBody AuthenticationRequest authenticationRequest){
    return authenticationService.authenticate(authenticationRequest);
  }

  @GetMapping("/")
  public AuthResponse reAuth(){
    return authenticationService.reAuthenticate();
  }

}
