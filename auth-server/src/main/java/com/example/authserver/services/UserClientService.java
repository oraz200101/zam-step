package com.example.authserver.services;

import com.example.authserver.models.dtos.UserLoginDto;

public interface UserClientService {
    UserLoginDto getUser(String email);
}
