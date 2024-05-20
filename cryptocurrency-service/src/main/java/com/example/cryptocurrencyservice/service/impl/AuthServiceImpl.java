package com.example.cryptocurrencyservice.service.impl;


import com.example.cryptocurrencyservice.model.authentication.AuthPrincipal;
import com.example.cryptocurrencyservice.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthPrincipal getAuthPrincipal() {
        return (AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
