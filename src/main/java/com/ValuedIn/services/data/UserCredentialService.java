package com.ValuedIn.services.data;

import com.ValuedIn.models.dto.requests.users.NewUser;
import com.ValuedIn.models.entities.users.UserCredentials;
import com.ValuedIn.repositories.UsersCredentialsRepository;
import com.ValuedIn.services.AuthenticationService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCredentialService {
  private final UsersCredentialsRepository usersCredentialsRepository;

  public void saveNewUser(NewUser newUser){
    UserCredentials userCredentials =
        new UserCredentials(
            newUser.getLogin(),
            newUser.getPassword(),
            false,
            null, null
        );

    userCredentials.setExpired(false);
    userCredentials.setLogin(newUser.getLogin());
    userCredentials.setPassword(newUser.getPassword());
    usersCredentialsRepository.save(userCredentials);

  }
  public UserCredentials getCredentialsFromLogin(String login){
    return usersCredentialsRepository.findUserByLogin(login);
  }

  protected List<UserCredentials> getAllUserCredentials(){
    return usersCredentialsRepository.findAll();
  }

  protected Page<UserCredentials> getUserCredentialsPaginated(boolean showExpired, Pageable pageable){
    return usersCredentialsRepository.getUsersPaginatedAndFiltered(showExpired, pageable);
  }

  public void changeExpiration(String login) {
    UserCredentials credentials = usersCredentialsRepository.findUserByLogin(login);
    credentials.setExpired(!credentials.isExpired());
    usersCredentialsRepository.save(credentials);
  }

  public void updateLastActive(UserCredentials userCredentials){
    userCredentials.setLastActive(LocalDateTime.now());
    usersCredentialsRepository.save(userCredentials);
  }
}
