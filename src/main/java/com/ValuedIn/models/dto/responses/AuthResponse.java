package com.ValuedIn.models.dto.responses;

import com.ValuedIn.enumerators.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
  private String token;
  private UserRole role;
}
