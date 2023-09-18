package com.example.demo.presentation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.testcomponent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest extends BaseControllerTest {

  @Autowired
  private MockMvc mockMvc;

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
}
