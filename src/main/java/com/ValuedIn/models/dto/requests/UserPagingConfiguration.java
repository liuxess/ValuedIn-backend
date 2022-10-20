package com.ValuedIn.models.dto.requests;


import com.ValuedIn.models.entities.UserCredentials;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class UserPagingConfiguration {

  private boolean showExpired;
  private int page;
  private int size;
  private String orderBy;
  private boolean ascending;

  public Sort getSort(){
    Sort sort = Sort.by(getOrderBy());
    return ascending ? sort.ascending() : sort.descending();
  }
  public String getOrderBy(){//assigning that it is a child source in case it does not exist on master
    return
        Arrays.stream(UserCredentials.class.getDeclaredFields()).anyMatch(field -> field.getName().equals(orderBy))
        ? orderBy
        : "userDetails."+orderBy;
  }

}