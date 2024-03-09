package com.example.authserver.services;

import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;

public interface AuthService {
    void registration(UserRegistrationRequest request);

    JwtResponseDto login(JwtRequestDto jwtRequestDto);
}
