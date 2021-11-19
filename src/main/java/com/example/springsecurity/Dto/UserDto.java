package com.example.springsecurity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
}
