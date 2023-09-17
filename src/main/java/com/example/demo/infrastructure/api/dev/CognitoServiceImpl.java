package com.example.demo.infrastructure.api.dev;

import com.example.demo.infrastructure.api.CognitoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "dev")
public class CognitoServiceImpl implements CognitoService {

  @Override
  public void userRegister(String username, String password) {
    System.out.println(username + "の登録が完了しました。");
  }
}
