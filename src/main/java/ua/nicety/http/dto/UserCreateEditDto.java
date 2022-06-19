package ua.nicety.http.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class UserCreateEditDto {

    @NotBlank
    String username;
    @NotBlank
    String rawPassword;
    @Email
    String email;
}
