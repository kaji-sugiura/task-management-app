package com.example.demo.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.domain.dto.UserUpdateDTO;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotExistsException;
import com.example.demo.infrastructure.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @DisplayName("should return true when email already exits")
  @Test
  public void test_isEmailAlreadyRegistered_should_return_true() {
    String email = "test@mail.com";
    when(userRepository.findByEmail(email)).thenReturn(
        Optional.of(User.builder().email(email).build()));
    assertTrue(userService.isEmailAlreadyRegistered(email));
  }

  @DisplayName("should return false when email doesn't exit")
  @Test
  public void test_isEmailAlreadyRegistered_should_return_false() {
    String email = "test@mail.com";
    when(userRepository.findByEmail(email)).thenReturn(
        Optional.empty());
    assertFalse(userService.isEmailAlreadyRegistered(email));
  }

  @DisplayName("should throw UserAlreadyExistsException when email already exits")
  @Test
  public void test_register_should_throw_UserAlreadyExistsException() {
    String email = "test@mail.com";
    when(userRepository.findByEmail(email)).thenReturn(
        Optional.of(User.builder().email(email).build()));
    UserAlreadyExistsException ex = assertThrows(UserAlreadyExistsException.class, () -> {
      userService.register(UserRegistrationDTO.builder().email(email).build());
    });
    assertEquals("メールアドレス = " + email + " のユーザは既に存在しています。", ex.getMessage());
  }

  @DisplayName("should throw User Not Exists Exception when user doesn't exists")
  @Test
  public void test_update_should_throw_UserNotExistsException() {
    UserUpdateDTO dto = UserUpdateDTO.builder().id(1L).email("test@test.com").build();
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(
        Optional.empty());
    when(userRepository.findById(dto.getId())).thenReturn(Optional.empty());
    UserNotExistsException ex = assertThrows(UserNotExistsException.class, () -> {
      userService.update(dto);
    });
    assertEquals("userId = " + dto.getId() + " のユーザは存在しません。", ex.getMessage());
  }
}
