package com.example.demo.infrastructure.api;

public interface CognitoService {

  /**
   * Cognito上のユーザプールに登録する
   *
   * @param username ユーザ名
   * @param password パスワード
   */
  void userRegister(String username, String password);
}
