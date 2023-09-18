package com.example.demo.domain.dto;

import com.example.demo.presentation.request.UserUpdateRequest;
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

  public static UserUpdateDTO from(UserUpdateRequest request, Long id) {
    return UserUpdateDTO.builder().id(id).nickName(request.getNickName()).email(request.getEmail())
        .build();
  }
}
