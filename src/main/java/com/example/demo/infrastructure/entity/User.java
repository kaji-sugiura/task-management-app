package com.example.demo.infrastructure.entity;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.dto.UserUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User Entity
 */
@Table(name = "Users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String nickName;

  @Column(unique = true)
  private String email;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  /**
   * UserRegistrationDTOからインスタンスを生成する
   *
   * @param userRegistrationDTO ユーザ登録DTO
   * @return Userエンティティ
   */
  public static User from(UserRegistrationDTO userRegistrationDTO) {
    return User.builder().nickName(userRegistrationDTO.getNickName())
        .email(userRegistrationDTO.getEmail()).createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now()).build();
  }

  public static User from(UserUpdateDTO dto, LocalDateTime createdAt) {
    return User.builder().userId(dto.getId()).nickName(dto.getNickName())
        .email(dto.getEmail()).createdAt(createdAt)
        .updatedAt(LocalDateTime.now()).build();
  }
}
