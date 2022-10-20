package com.ValuedIn.services;

import com.ValuedIn.factories.UserInfoFactory;
import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.models.dto.requests.UpdatedUser;
import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.models.entities.UserDetails;
import com.ValuedIn.models.entities.UserCredentials;
import com.ValuedIn.repositories.UserDetailsRepository;
import java.util.Collection;
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

  private final UserDetailsRepository userDetailsRepository;
  private final UserCredentialService userCredentialService;

  private final UserInfoFactory userInfoFactory = new UserInfoFactory();

  public Optional<UserInfo> getUserInfo(String login){
    UserCredentials userCredentials = userCredentialService.getCredentialsFromLogin(login);
    if(userCredentials == null)
      return Optional.empty();

    UserDetails userDetails = userDetailsRepository.findByLogin(login);

    return Optional.of(
        userDetails == null
        ? userInfoFactory.getInfoFromCredentials(userCredentials)
        : userInfoFactory.getInfoFromCredentialsAndDetails(userCredentials, userDetails)
    );
  }

  public List<UserInfo> getAllUsers(){
    Collection<UserCredentials> userCredentials = userCredentialService.getAllUserCredentials();
    Collection<UserDetails> userDetails = userDetailsRepository.findAll();
    return mapCredentialsAndDetails(userCredentials, userDetails);
  }

  private List<UserInfo> mapCredentialsAndDetails(Collection<UserCredentials> userCredentials, Collection<UserDetails> userDetails){
    return userCredentials.stream().parallel().map(credentials -> createInfoFromCredentialsAndUnfilteredDetailList(credentials, userDetails)).toList();
  }

  private UserInfo createInfoFromCredentialsAndUnfilteredDetailList(UserCredentials credentials, Collection<UserDetails> details){
    Optional<UserDetails> userDetails = getDetailsFromListAccordingToCredentials(credentials, details);

    return userDetails.isEmpty()
          ? userInfoFactory.getInfoFromCredentials(credentials)
          : userInfoFactory.getInfoFromCredentialsAndDetails(credentials, userDetails.get());
  }

  private Optional<UserDetails> getDetailsFromListAccordingToCredentials(UserCredentials credentials, Collection<UserDetails> details){
      return details.stream().filter(userDetails -> userDetails.getLogin().equals(credentials.getLogin())).findFirst();
  }

  public Page<UserInfo> getPaginatedInfo(boolean showExpired, Pageable pageable){
     Page<UserCredentials> userCredentials = userCredentialService.getUserCredentialsPaginated(showExpired, pageable);
     List<UserDetails> userDetails = userDetailsRepository.findAllByLoginIn(userCredentials.map(UserCredentials::getLogin).toList());
     List<UserInfo> userInfo = mapCredentialsAndDetails(userCredentials.getContent(), userDetails);
     Page<UserInfo> userInfoPaged = new PageImpl<>(userInfo, userCredentials.getPageable(), userCredentials.getTotalElements());
     return userInfoPaged;
  }

  public void saveNewUser(NewUser newUser){
    userCredentialService.saveNewUser(newUser);
    userDetailsRepository.save(new UserDetails(newUser.getLogin(), newUser.getRole(), newUser.getFirstName(), newUser.getLastName()));
  }

  public void updateUser(UpdatedUser updatedUser) {
    UserDetails userDetails = userDetailsRepository.findByLogin(updatedUser.getLogin());
    userDetails.setFirstName(updatedUser.getFirstName());
    userDetails.setLastName(updatedUser.getLastName());
    userDetails.setRole(updatedUser.getRole());
    userDetailsRepository.save(userDetails);
  }

  public void changeExpiration(String login) {
    userCredentialService.changeExpiration(login);
  }
}
