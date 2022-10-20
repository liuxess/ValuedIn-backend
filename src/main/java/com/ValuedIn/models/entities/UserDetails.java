package com.ValuedIn.models.entities;

import com.ValuedIn.enumerators.UserRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user_details")
public class UserDetails {

    @Id
    @Column(name = "login")
    private String login;

    @Enumerated
    @Nullable
    @Column(name = "role")
    private UserRole role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


}
