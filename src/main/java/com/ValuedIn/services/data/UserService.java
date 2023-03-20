package com.ValuedIn.services.data;

import com.ValuedIn.events.NewUserEvent;
import com.ValuedIn.models.dto.requests.users.NewUser;
import com.ValuedIn.models.dto.requests.users.UpdatedUser;
import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.models.entities.users.UserCredentials;
import com.ValuedIn.models.mappers.DTOMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserCredentialService userCredentialService;
  private final UserDetailsService userDetailsService;
  private final DTOMapper dtoMapper = new DTOMapper();


  public Optional<UserInfo> getUserInfo(String login){
    UserCredentials userCredentials = userCredentialService.getCredentialsFromLogin(login);
    if(userCredentials == null)
      return Optional.empty();

    return Optional.of(dtoMapper.mapToUserInfo(userCredentials));
  }

  public List<UserInfo> getAllUsers(){
    return userCredentialService.getAllUserCredentials().stream().parallel().map(dtoMapper::mapToUserInfo).toList();
  }

  public Page<UserInfo> getPaginatedUserInfo(boolean showExpired, Pageable pageable){
     Page<UserCredentials> userCredentials = userCredentialService.getUserCredentialsPaginated(showExpired, pageable);
     List<UserInfo> userInfo = userCredentials.stream().parallel().map(dtoMapper::mapToUserInfo).toList();
     return new PageImpl<>(userInfo, userCredentials.getPageable(), userCredentials.getTotalElements());
  }

  public void saveNewUser(NewUser newUser){
    userCredentialService.saveNewUser(newUser);
    userDetailsService.save(newUser);
    new NewUserEvent(newUser);
  }

  public void updateUser(UpdatedUser updatedUser) {
    userDetailsService.save(updatedUser);
  }

  public void changeExpiration(String login) {
    userCredentialService.changeExpiration(login);
  }
}
