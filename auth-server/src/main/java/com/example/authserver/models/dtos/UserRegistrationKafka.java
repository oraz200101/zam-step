package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserRegistrationKafka {
    private Long   id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private String password;
    private String birthDate;
}
