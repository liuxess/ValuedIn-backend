package com.ValuedIn.models.dto.responses;

import com.ValuedIn.enumerators.UserRole;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

    private String login;
    private String firstName;
    private String lastName;
    private UserRole role;
    private LocalDateTime lastActive;
    private boolean isExpired;

}
