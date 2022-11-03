package com.ValuedIn.models.entities.messaging;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tbl_Messaging_Messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatMessage {

  @Id
  @Column(name = "messageid")
  private long messageID;
  
  @Column(name = "chatid")
  private long chatId;
  @Column
  private String login;
  @Column
  private String message;
  @Column
  private LocalDateTime created;

}
