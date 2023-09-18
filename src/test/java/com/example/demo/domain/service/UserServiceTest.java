package com.example.demo.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.demo.domain.dto.UserRegistrationDTO;
import com.example.demo.exception.UserAlreadyExistsException;
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

  @DisplayName("should_throw_UserAlreadyExistsException when email already exits")
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
}
