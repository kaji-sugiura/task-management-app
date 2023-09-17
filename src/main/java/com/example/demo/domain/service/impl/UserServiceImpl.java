package com.example.demo.domain.service.impl;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.service.UserService;
import com.example.demo.infrastructure.api.CognitoService;
import com.example.demo.infrastructure.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final CognitoService cognitoService;

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(CognitoService cognitoService, UserRepository userRepository) {
    this.cognitoService = cognitoService;
    this.userRepository = userRepository;
  }


  /**
   * 該当のEメールで登録されているユーザが既に存在するかを判定する
   *
   * @param email メールアドレス
   * @return 存在する場合true 存在しない場合false
   */
  @Override
  public Boolean isEmailAlreadyRegistered(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    return user.isPresent();
  }

  /**
   * ユーザ登録
   *
   * @param request 登録情報
   * @throws Exception 例外(DB接続)
   */
  @Override
  public void register(UserRegistrationDTO request) throws Exception {
    if (isEmailAlreadyRegistered(request.getEmail())) {
      // TODO: 該当のメールアドレスが既に存在する場合の例外を投げる
      throw new Exception();
    }
    userRepository.save(User.from(request));
    cognitoService.userRegister(request.getEmail(), request.getPassword());
  }
}
