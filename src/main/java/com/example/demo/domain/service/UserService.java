package com.example.demo.domain.service;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.dto.UserUpdateDTO;

/**
 * user service
 */
public interface UserService {

  /**
   * 該当のEメールで登録されているユーザが既に存在するかを判定する
   *
   * @param email メールアドレス
   * @return 存在する場合true 存在しない場合false
   */
  Boolean isEmailAlreadyRegistered(String email);

  /**
   * ユーザ登録
   *
   * @param request 登録情報
   * @throws Exception DB接続などの例外
   */
  void register(UserRegistrationDTO request) throws Exception;

  /**
   * ユーザ情報更新
   *
   * @param request 更新情報
   * @throws Exception DB接続などの例外
   */
  void update(UserUpdateDTO request) throws Exception;
}
