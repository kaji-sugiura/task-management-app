package com.example.demo.domain.service.impl;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.dto.UserUpdateDTO;
import com.example.demo.domain.service.UserService;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotExistsException;
import com.example.demo.infrastructure.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
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
   * @throws UserAlreadyExistsException 例外(DB接続)
   */
  @Override
  @Transactional
  public void register(UserRegistrationDTO request) throws Exception {
    if (isEmailAlreadyRegistered(request.getEmail())) {
      throw new UserAlreadyExistsException("メールアドレス = " + request.getEmail() + " のユーザは既に存在しています。");
    }
    userRepository.save(User.from(request));
  }

  /**
   * ユーザ情報更新
   *
   * @param request 更新情報
   * @throws Exception DB接続などの例外
   */
  @Override
  @Transactional
  public void update(UserUpdateDTO request) throws Exception {
    if (isEmailAlreadyRegistered(request.getEmail())) {
      throw new UserAlreadyExistsException("メールアドレス = " + request.getEmail() + " のユーザは既に存在しています。");
    }
    Optional<User> user = userRepository.findById(request.getId());
    if (user.isEmpty()) {
      throw new UserNotExistsException("userId = " + request.getId() + " のユーザは存在しません。");
    }
    User updatedUser = User.from(request, user.get().getCreatedAt());
    userRepository.save(updatedUser);
  }
}
