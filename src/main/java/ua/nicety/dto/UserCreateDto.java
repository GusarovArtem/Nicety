package ua.nicety.dto;

import lombok.Value;

@Value
public class UserCreateDto {

    String username;
    String rawPassword;
    String email;
}
