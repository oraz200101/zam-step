package com.example.authserver.models.dtos;

import com.example.authserver.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private String    email;
    private String    password;
    private Set<Role> roles;
}

