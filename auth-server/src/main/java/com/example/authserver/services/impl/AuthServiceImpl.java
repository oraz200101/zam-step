package com.example.authserver.services.impl;

import com.example.authserver.exception.NotFoundException;
import com.example.authserver.jwt.JwtTokenProvider;
import com.example.authserver.kafka.producer.KafkaProducer;
import com.example.authserver.mapper.AuthMapper;
import com.example.authserver.models.dtos.AuthUpdateDto;
import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.models.entities.AuthEntity;
import com.example.authserver.repository.AuthRepository;
import com.example.authserver.services.AuthService;
import com.example.authserver.utils.validation.UserValidator;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AuthServiceImpl implements AuthService {

    private final AuthMapper       mapper;
    private final KafkaProducer    kafkaProducer;
    private final AuthRepository   repository;
    private final PasswordEncoder  passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public JwtResponseDto registration(final UserRegistrationRequest request) {
        UserValidator.validate(request);

        AuthEntity entity = repository.save(mapper.mapToEntity(request));

        kafkaProducer.sendModelUserRegistration(mapper.mapToKafka(entity, request));

        return login(JwtRequestDto.of(request.getEmail(), request.getPassword()));
    }

    @Override
    public void update(AuthUpdateDto updateDto) {
        AuthEntity entity = mapper.mapToEntity(updateDto);

        repository.save(entity);
    }

    @Override
    public JwtResponseDto login(final JwtRequestDto request) {
        JwtResponseDto response = new JwtResponseDto();

        if (!isRightLoginAndPassword(request)) {
            throw new RuntimeException("wrong password or email");
        }

        AuthEntity entity = repository.findByEmail(request.getEmail())
                                      .orElseThrow(() -> new NotFoundException("user with email: " + request.getEmail() + " not found"));

        response.setAccessToken(jwtTokenProvider.createAccessToken(entity.getEmail(), entity.getRoles()));
        response.setRefreshToken(jwtTokenProvider.createRefreshToken(entity.getEmail(), entity.getRoles()));

        return response;
    }

    @Override
    public boolean tokenIsValid(String token) {
        try {
            return jwtTokenProvider.isValid(token);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public Authentication produceAuthentication(String token) {
        return jwtTokenProvider.getAuthentication(token);
    }

    private boolean isRightLoginAndPassword(JwtRequestDto request) {
        AuthEntity entity = repository.findByEmail(request.getEmail())
                                      .orElseThrow(() -> new NotFoundException("user with email: " + request.getEmail() + " not found"));

        return passwordEncoder.matches(request.getPassword(), entity.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                         .orElseThrow(() -> new NotFoundException("user with email: " + username + " not found"));
    }

}
