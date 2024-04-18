package com.example.authserver.models.kafka;

import lombok.Getter;
import lombok.Setter;

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
