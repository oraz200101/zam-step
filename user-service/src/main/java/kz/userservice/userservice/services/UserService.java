package kz.userservice.userservice.services;

import kz.userservice.userservice.models.dtos.UserProfileDto;
import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.models.dtos.UserUpdateRequestDto;

public interface UserService {

    void createUser(UserRegistrationDto registrationRequest);

    UserProfileDto updateProfile(UserUpdateRequestDto request);

}
