package com.example.authserver.controller;

import com.example.authserver.models.dtos.JwtRequestDto;
import com.example.authserver.models.dtos.JwtResponseDto;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationRequest request){
        service.registration(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto request){
         return ResponseEntity.ok(service.login(request));
    }

    @GetMapping("/check-token")
    public boolean tokenIsValid(@RequestParam("token") String token){
        return service.tokenIsValid(token);
    }

    @GetMapping("/produce-authentication")
    public ResponseEntity<Authentication> produceAuthentication(@RequestParam String token){
        return ResponseEntity.ok(service.produceAuthentication(token));
    }

}
