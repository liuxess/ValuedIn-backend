package com.ValuedIn.models.entities.messaging;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tbl_messaging_chats")
@NoArgsConstructor
@Getter
@Setter
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chatid")
  private long chatId;

  @OneToMany(mappedBy = "chatId")
  @OrderBy("login DESC")
  Collection<ChatMessage> chatMessages;

  @OneToMany(mappedBy = "chat")
  Collection<ChatParticipant> chatParticipants;

}
