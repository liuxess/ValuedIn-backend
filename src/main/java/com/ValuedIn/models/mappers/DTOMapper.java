package com.ValuedIn.models.mappers;

import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.models.entities.UserCredentials;
import com.ValuedIn.models.entities.UserDetails;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DTOMapper {

  public UserInfo mapToUserInfo(UserCredentials credentials){
    UserDetails details = credentials.getUserDetails();

    return details == null
        ? new UserInfo(credentials.getLogin(), null, null, null, null, null, credentials.getLastActive(), credentials.isExpired())
        : new UserInfo(credentials.getLogin(), details.getFirstName(), details.getLastName(), details.getRole(),
            details.getEmail(), details.getTelephone(), credentials.getLastActive(), credentials.isExpired());
  }
}
