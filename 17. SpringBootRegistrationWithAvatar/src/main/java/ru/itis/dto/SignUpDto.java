package ru.itis.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
