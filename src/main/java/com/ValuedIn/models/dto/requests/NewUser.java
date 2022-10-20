package com.ValuedIn.models.dto.requests;

import com.ValuedIn.enumerators.UserRole;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewUser {

  private String login;

  @Enumerated
  private UserRole role;
  private String firstName;
  private String lastName;
  private String password;

}
