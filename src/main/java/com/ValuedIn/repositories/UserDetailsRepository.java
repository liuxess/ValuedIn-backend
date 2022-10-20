package com.ValuedIn.repositories;

import com.ValuedIn.models.entities.UserDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

    UserDetails findByLogin(String login);

    List<UserDetails> findAllByLoginIn(List<String> logins);
}
