package com.ValuedIn.models.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_user_credentials")
public class UserCredentials {

  @Id
  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "is_expired")
  private boolean isExpired = false;

  @Nullable
  @Column(name = "Last_Active")
  private LocalDateTime lastActive;

}
