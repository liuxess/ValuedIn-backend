package com.ValuedIn.factories;

import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.models.entities.UserCredentials;
import com.ValuedIn.models.entities.UserDetails;
import com.sun.istack.NotNull;

public class UserInfoFactory {

  public UserInfoFactory(){

  }

  public UserInfo getInfoFromCredentialsAndDetails(UserCredentials credentials, @NotNull UserDetails details){
    return new UserInfo(
        credentials.getLogin(), details.getFirstName(), details.getLastName(), details.getRole(), credentials.getLastActive(), credentials.isExpired()
    );
  }

  public UserInfo getInfoFromCredentials(UserCredentials credentials){
    return new UserInfo(credentials.getLogin(), null, null, null, credentials.getLastActive(), credentials.isExpired());
  }

}
