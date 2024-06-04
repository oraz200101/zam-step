package com.example.authserver.services;

import com.example.authserver.models.dtos.AuthUpdateDto;
import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    JwtResponseDto registration(UserRegistrationRequest request);

    void update(AuthUpdateDto authUpdateDto);

    JwtResponseDto login(JwtRequestDto jwtRequestDto);

    boolean tokenIsValid(String token);

    Authentication produceAuthentication(String token);

}
