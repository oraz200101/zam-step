package com.example.authserver.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {
    private String email;
    private String password;

    public static JwtRequestDto of(String email, String password) {
        JwtRequestDto requestDto = new JwtRequestDto();

        requestDto.email    = email;
        requestDto.password = password;

        return requestDto;
    }

}
