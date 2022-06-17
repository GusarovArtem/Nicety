package ua.nicety.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @NotEmpty(message = "Please write your username")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 50 characters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Please write your email")
    @Email(message = "Please write a valid email")
    private String email;

    private Role role;

}