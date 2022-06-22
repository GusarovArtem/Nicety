package ua.nicety.http.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class UserCreateEditDto {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 30, message = "Username must be between 2 and 50 characters")
    String username;
    @NotBlank
    String rawPassword;

    @Email(message = "Please write your email")
    @Email(message = "Please write a valid email")
    String email;
}
