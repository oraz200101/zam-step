package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUpdateDto {
    private Long   id;
    private String email;
    private String password;
}
