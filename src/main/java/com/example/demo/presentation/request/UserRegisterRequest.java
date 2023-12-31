package com.example.demo.presentation.request;

import com.example.demo.presentation.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@PasswordMatches
public class UserRegisterRequest {

  @NotBlank(message = "nick name must not be blank or null")
  @Size(max = 50, message = "nick name must be less than 50 characters")
  private String nickName;

  @NotBlank(message = "email must not be blank or null")
  @Email(message = "email address format is incorrect")
  @Size(max = 50, message = "email must be less than 50 characters")
  private String email;

  @NotBlank(message = "password must not be blank or null")
  @Size(min = 8, message = "password must be greater than 8 characters")
  private String password;

  @NotBlank(message = "password must not be blank or null")
  @Size(min = 8, message = "confirmPassword must be greater than 8 characters")
  private String confirmPassword;

}
