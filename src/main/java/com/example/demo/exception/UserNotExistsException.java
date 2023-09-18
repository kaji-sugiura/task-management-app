package com.example.demo.exception;

public class UserNotExistsException extends Exception {

  public UserNotExistsException(String message) {
    super(message);
  }
}
