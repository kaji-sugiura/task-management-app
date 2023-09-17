package com.example.demo.presentation.validation;


import com.example.demo.presentation.request.UserRegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(final PasswordMatches constraintAnnotation) {
  }

  @Override
  public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
    final UserRegisterRequest user = (UserRegisterRequest) obj;
    return user.getPassword().equals(user.getConfirmPassword());
  }
}
