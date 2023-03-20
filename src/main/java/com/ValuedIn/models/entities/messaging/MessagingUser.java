package com.ValuedIn.models.entities.messaging;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user_details")
@NoArgsConstructor
@Getter
@Setter
public class MessagingUser {

  @Id
  private String login;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(mappedBy="messagingUser")
  private Collection<ChatParticipant> participatedChats;

  public String getFullName(){
    return firstName + " " + lastName;
  }



}
