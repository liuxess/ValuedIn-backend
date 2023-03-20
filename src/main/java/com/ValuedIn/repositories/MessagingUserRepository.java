package com.ValuedIn.repositories;

import com.ValuedIn.models.entities.messaging.MessagingUser;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagingUserRepository extends JpaRepository<MessagingUser, String> {
  MessagingUser getMessagingUserByLogin(String login);

  Collection<MessagingUser> getMessagingUsersByLoginIn(Collection<String> logins);
}