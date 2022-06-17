package ua.nicety.http.dto;

import lombok.Value;

@Value
public class UserCreateEditDto {

    String username;
    String rawPassword;
    String email;
}
