package com.ValuedIn.repositories;

import com.ValuedIn.models.entities.UserCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCredentialsRepository extends JpaRepository<UserCredentials, String> {
    UserCredentials findUserByLogin(String login);

    @Query("SELECT U FROM tbl_user_credentials U WHERE U.isExpired = :showExpired")
    Page<UserCredentials> getUsersPaginatedAndFiltered(@Param("showExpired") Boolean showExpired, Pageable p);

}

