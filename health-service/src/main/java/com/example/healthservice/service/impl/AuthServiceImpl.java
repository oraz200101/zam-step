package com.example.healthservice.service.impl;

import com.example.healthservice.model.authentication.AuthPrincipal;
import com.example.healthservice.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthPrincipal getAuthPrincipal() {
        return (AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
