package com.example.authserver.services.impl;

import com.example.authserver.kafka.producer.KafkaProducer;
import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.services.AuthService;
import com.example.authserver.services.UserClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KafkaProducer     kafkaProducer;
    private final UserClientService clientService;

    @Override
    public void registration(UserRegistrationRequest request) {
        kafkaProducer.sendModelUserRegistration(request);
    }

    @Override
    public JwtResponseDto login(JwtRequestDto jwtRequestDto) {
        return null;
    }
}
