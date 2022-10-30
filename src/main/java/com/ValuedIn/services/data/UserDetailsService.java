package com.ValuedIn.services.data;

import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.models.dto.requests.UpdatedUser;
import com.ValuedIn.models.entities.UserDetails;
import com.ValuedIn.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
  private final UserDetailsRepository userDetailsRepository;

  public void save(NewUser newUser){
    userDetailsRepository.save(new UserDetails(newUser.getLogin(), newUser.getRole(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getTelephone()));
  }

  public void save(UpdatedUser updatedUser){
    UserDetails userDetails = userDetailsRepository.findByLogin(updatedUser.getLogin());
    userDetails.setFirstName(updatedUser.getFirstName());
    userDetails.setLastName(updatedUser.getLastName());
    userDetails.setRole(updatedUser.getRole());
    userDetails.setEmail(updatedUser.getEmail());
    userDetails.setTelephone(userDetails.getTelephone());
    save(userDetails);
  }

  public void save(UserDetails userDetails){
    userDetailsRepository.save(userDetails);
  }
}
