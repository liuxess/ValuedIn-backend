package com.ValuedIn.models.entities.messaging;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tbl_messaging_chatparticipation")
@NoArgsConstructor
@Getter
@Setter
public class ChatParticipant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name="login")
  private MessagingUser messagingUser;

  @ManyToOne()
  @JoinColumn(name="chatid")
  private Chat chat;

  public ChatParticipant(Chat chat, MessagingUser messagingUser){
    this.chat = chat;
    this.messagingUser = messagingUser;
  }

}
