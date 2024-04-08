package kz.userservice.userservice.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    private Long   id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String password;
    private String birthDate;
}
