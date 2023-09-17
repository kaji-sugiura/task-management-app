package com.example.demo.domain.dto;

import com.example.demo.presentation.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * ユーザ登録DTO
 */
@AllArgsConstructor
@Builder
@Getter
public class UserRegistrationDTO {

  private String nickName;

  private String email;

  private String password;

  public static UserRegistrationDTO from(UserRegisterRequest request) {
    return UserRegistrationDTO.builder().nickName(request.getNickName()).email(request.getEmail())
        .password(request.getPassword()).build();
  }
}
