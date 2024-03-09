package kz.userservice.userservice.models.dtos;

import kz.userservice.userservice.models.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserToAuthService {
    private Long      id;
    private String    email;
    private Set<Role> roles;
}
