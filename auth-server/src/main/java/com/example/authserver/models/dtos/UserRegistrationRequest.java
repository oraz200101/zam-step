package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private String password;
    private String birthDate;
}
