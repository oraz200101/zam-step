package kz.userservice.userservice.services.impl;

import kz.userservice.userservice.services.AuthService;
import kz.userservice.userservice.mapper.UserMapper;
import kz.userservice.userservice.models.dtos.UserProfileDto;
import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.models.dtos.UserUpdateRequestDto;
import kz.userservice.userservice.models.entities.UserEntity;
import kz.userservice.userservice.repository.UserRepository;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper            mapper;
    private final UserRepository repository;
    private final AuthService    authAdapter;

    @Override
    public void createUser(UserRegistrationDto dto) {
        repository.save(mapper.mapToEntity(dto));
    }

    @Override
    public UserProfileDto updateProfile(UserUpdateRequestDto request) {
        UserEntity entity = authAdapter.currentUser();

        entity = mapper.mapToEntity(entity, request);

        repository.save(entity);

        return mapper.mapToDto(entity);
    }

    @Override
    public UserProfileDto getProfile() {
        UserEntity entity = authAdapter.currentUser();

        return mapper.mapToDto(entity);
    }
}
