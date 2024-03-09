package kz.userservice.userservice.services;

import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.models.dtos.UserToAuthService;

public interface UserService {

    void createUser(UserRegistrationDto registrationRequest);

    UserToAuthService toAuthService(Long id);
}
