package com.ValuedIn.models.dto.requests.messaging;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewChat {
  private Collection<String> participatingUsers;
}
