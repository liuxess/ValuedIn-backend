package com.ValuedIn.models.dto.responses;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserParticipatedChat {

  private long chatId;

  private Collection<String> participantNames;

}
