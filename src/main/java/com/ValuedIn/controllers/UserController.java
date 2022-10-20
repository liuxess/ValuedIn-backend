package com.ValuedIn.controllers;

import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.models.dto.requests.UpdatedUser;
import com.ValuedIn.models.dto.requests.UserPagingConfiguration;
import com.ValuedIn.models.dto.responses.UserInfo;
import com.ValuedIn.services.UserService;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  @GetMapping("/{login}")
  public ResponseEntity<UserInfo> getUser(@PathVariable("login") String login) {
    Optional<UserInfo> userInfo = userService.getUserInfo(login);

    if(userInfo.isEmpty())
        return ResponseEntity.notFound().build();

    return ResponseEntity.ok(userInfo.get());
  }

  @GetMapping("/")
  public Collection<UserInfo> getUsers() {
    return userService.getAllUsers();
  }


  @PostMapping("/")
  public Page<UserInfo> getAllUsersPaginated(@RequestBody UserPagingConfiguration config) {
    Pageable pageable = PageRequest.of(config.getPage(), config.getSize(), config.getSort());
    return userService.getPaginatedUserInfo(config.isShowExpired(), pageable);
  }

  @PostMapping("/new")
  public void createUser(@RequestBody NewUser newUser){
    userService.saveNewUser(newUser);
  }

  @PutMapping("/")
  public void updateUser(@RequestBody UpdatedUser updatedUser){
    userService.updateUser(updatedUser);
  }

  @DeleteMapping("/{login}")
  public void expireUser(@PathVariable("login") String login) {
    userService.changeExpiration(login);
  }

}
