package com.example.demo.presentation.controller;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.dto.UserUpdateDTO;
import com.example.demo.domain.service.UserService;
import com.example.demo.presentation.request.UserRegisterRequest;
import com.example.demo.presentation.request.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * ユーザ登録API
   *
   * @param request リクエストBody
   * @throws Exception 例外
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@Valid @RequestBody UserRegisterRequest request)
      throws Exception {
    UserRegistrationDTO dto = UserRegistrationDTO.from(request);
    userService.register(dto);
  }

  /**
   * ユーザ情報更新API
   *
   * @param id      ユーザID
   * @param request リクエストBody
   * @throws Exception 例外
   */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void update(@PathVariable long id, @Valid @RequestBody UserUpdateRequest request)
      throws Exception {
    UserUpdateDTO dto = UserUpdateDTO.from(request, id);
    userService.update(dto);
  }
}
