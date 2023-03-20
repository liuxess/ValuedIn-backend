package com.ValuedIn.models.dto.responses;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChattingMessage {

  private String fullName;
  private String messageContent;
  private LocalDateTime messageSent;

}
