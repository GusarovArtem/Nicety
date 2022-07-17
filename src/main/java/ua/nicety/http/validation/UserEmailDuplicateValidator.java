package ua.nicety.http.validation;

import lombok.RequiredArgsConstructor;
import ua.nicety.http.validation.annotation.UserEmailDuplicateValidate;
import ua.nicety.service.user.UserServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserEmailDuplicateValidator implements ConstraintValidator<UserEmailDuplicateValidate, String> {

    private final UserServiceImpl userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getByEmail(email) == null;
    }
}
