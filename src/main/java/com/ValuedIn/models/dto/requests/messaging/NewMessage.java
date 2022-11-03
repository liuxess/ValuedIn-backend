package com.ValuedIn.models.dto.requests.messaging;

import lombok.Data;

@Data
public class NewMessage {
  private long chatId;
  private String message;
}
