package com.example.authserver.services.impl;

import com.example.authserver.jwt.JwtTokenProvider;
import com.example.authserver.kafka.producer.KafkaProducer;
import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserLoginDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.services.AuthService;
import com.example.authserver.services.UserClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KafkaProducer         kafkaProducer;
    private final UserClientService     userClientService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider      jwtTokenProvider;

    @Override
    public void registration(final UserRegistrationRequest request) {
        kafkaProducer.sendModelUserRegistration(request);
    }

    @Override
    public JwtResponseDto login(final JwtRequestDto request) {
        JwtResponseDto response = new JwtResponseDto();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        UserLoginDto user = userClientService.getUser(request.getEmail());

        response.setAccessToken(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRoles()));
        response.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getEmail(), user.getRoles()));

        return response;
    }
}
