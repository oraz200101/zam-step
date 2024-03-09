package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {
    private Long   id;
    private String accessToken;
    private String refreshToken;
}
