package com.example.authserver.services;

import com.example.authserver.models.dtos.UserLoginDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserClientService extends UserDetailsService {
    UserLoginDto getUser(String email);
}
