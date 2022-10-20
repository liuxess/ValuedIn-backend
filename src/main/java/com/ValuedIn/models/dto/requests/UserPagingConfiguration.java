package com.ValuedIn.models.dto.requests;


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
    Sort sort = Sort.by(orderBy);
    return ascending ? sort.ascending() : sort.descending();
  }
}