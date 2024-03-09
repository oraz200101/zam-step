package com.example.authserver.services;

import com.example.authserver.models.dtos.UserDto;

public interface UserClientService {

    UserDto getUser(Long id);


}
