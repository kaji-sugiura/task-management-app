package com.example.demo.presentation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.infrastructure.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.testcomponent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    cleanUp();
  }

  @DisplayName("user register api test")
  @Test
  public void test_User_Register_ok() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserRegisterRequest.json");
    mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isCreated());
  }

  @DisplayName("user register api test of user already exists")
  @Test
  public void test_User_Register_UserAlreadyExits() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserRegisterRequest.json");
    mockMvc.perform(post("/users")
        .contentType("application/json")
        .content(requestBody));

    mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isBadRequest());
  }

  @DisplayName("user register api test of bad parameter")
  @Test
  public void test_User_Register_bad_parameter() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserRegisterBadRequest.json");
    mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isBadRequest());
  }

  @DisplayName("user update api test")
  @Test
  public void test_User_Update_ok() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserRegisterRequest.json");
    mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isCreated());
    User registeredUser = userRepository.findByEmail("test@mail.com").orElse(null);
    assertNotNull(registeredUser);

    String requestBody2 = TestUtils.readJsonFile(
        "json/request/UserController/UserUpdateRequest.json");
    long id = registeredUser.getUserId();
    mockMvc.perform(put("/users/" + id)
            .contentType("application/json")
            .content(requestBody2))
        .andExpect(status().isOk());
    User updatedUser = userRepository.findById(id).orElse(null);
    assertNotNull(updatedUser);
    assertEquals("test update", updatedUser.getNickName());
    assertEquals("test2@mail.com", updatedUser.getEmail());
  }

  @DisplayName("user update api test of email already exists")
  @Test
  public void test_User_Update_bad_parameter() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserRegisterRequest.json");
    mockMvc.perform(post("/users")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isCreated());
    User registeredUser = userRepository.findByEmail("test@mail.com").orElse(null);
    assertNotNull(registeredUser);

    String requestBody2 = TestUtils.readJsonFile(
        "json/request/UserController/UserUpdateBadRequest.json");
    long id = registeredUser.getUserId();
    mockMvc.perform(put("/users/" + id)
            .contentType("application/json")
            .content(requestBody2))
        .andExpect(status().isBadRequest());
  }

  @DisplayName("user update api test of user doesn't exists")
  @Test
  public void test_User_Update_user_not_exists() throws Exception {
    String requestBody = TestUtils.readJsonFile(
        "json/request/UserController/UserUpdateBadRequest.json");
    mockMvc.perform(put("/users/" + 100)
            .contentType("application/json")
            .content(requestBody))
        .andExpect(status().isNotFound());
  }

}
