package com.example.authserver.models.dtos;

import com.example.authserver.models.enums.Role;
import lombok.AllArgsConstructor;

import java.util.Set;


@AllArgsConstructor
public class UserLoginDto {
    private Long      id;
    private String    email;
    private Set<Role> roles;
}

