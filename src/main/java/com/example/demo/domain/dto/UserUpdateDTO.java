package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserUpdateDTO {

  private Long id;

  private String nickName;

  private String email;

  private String password;

  public static UserUpdateDTO from() {
    return null;
  }
}
