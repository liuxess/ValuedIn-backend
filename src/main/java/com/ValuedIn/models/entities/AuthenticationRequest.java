package com.ValuedIn.models.entities;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String login;
    private String password;

}
