package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
}
