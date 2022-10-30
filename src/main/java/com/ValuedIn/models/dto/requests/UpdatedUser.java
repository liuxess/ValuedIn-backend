package com.ValuedIn.models.dto.requests;


import com.ValuedIn.enumerators.UserRole;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatedUser {

  private String login;
  private String firstName;
  private String lastName;
  @Enumerated
  private UserRole role;
  private String email;
  private String telephone;
}
