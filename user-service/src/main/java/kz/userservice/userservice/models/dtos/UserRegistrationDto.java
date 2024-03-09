package kz.userservice.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private String password;
    private String birthDate;
}
