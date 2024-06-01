package kz.userservice.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileDto {
    private Long      id;
    private String    email;
    private String    firstname;
    private String    lastname;
    private String    patronymic;
    private LocalDate birthDate;
}
