package com.example.authserver.services.impl;

import com.example.authserver.models.dtos.UserLoginDto;
import com.example.authserver.services.UserClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.rmi.RemoteException;


@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {

    private final RestClient   userClient;
    private final ObjectMapper objectMapper;

    @Override
    public UserLoginDto getUser(String email) {
        return userClient.get().uri(UriComponentsBuilder.fromPath("/user/to-auth")
                                                        .queryParam("email", email)
                                                        .toUriString()).exchange(((request, response) -> {
            if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204))) {
                throw new RuntimeException();
            } else if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
                return objectMapper.readValue(response.getBody(), UserLoginDto.class);
            } else {
                throw new RemoteException();
            }
        }));
    }
}
