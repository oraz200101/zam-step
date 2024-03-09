package kz.userservice.userservice.services;

import kz.userservice.userservice.models.dtos.UserRegistrationRequest;
import kz.userservice.userservice.models.dtos.UserToAuthService;

public interface UserService {

    void createUser(UserRegistrationRequest registrationRequest);

    UserToAuthService toAuthService(Long id);
}
