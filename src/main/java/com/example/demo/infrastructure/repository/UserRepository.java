package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * メールアドレスをキーにユーザを探す
   *
   * @param email メールアドレス
   * @return ユーザエンティティ
   */
  Optional<User> findByEmail(String email);
}
