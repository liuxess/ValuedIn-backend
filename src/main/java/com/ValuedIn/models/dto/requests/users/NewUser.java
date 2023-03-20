package com.ValuedIn.models.dto.requests.users;

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
  private String email;
  private String telephone;

}
