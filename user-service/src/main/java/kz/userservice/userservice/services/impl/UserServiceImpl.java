package kz.userservice.userservice.services.impl;

import kz.userservice.userservice.mapper.UserMapper;
import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.models.dtos.UserToAuthService;
import kz.userservice.userservice.models.entities.UserEntity;
import kz.userservice.userservice.repository.UserRepository;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper     mapper;
    private final UserRepository repository;

    @Override
    public void createUser(UserRegistrationDto dto) {
        repository.save(mapper.mapToEntity(dto));
    }

    @Override
    public UserToAuthService toAuthService(String email) {

        UserEntity user = repository.findByEmail(email)
                                    .orElseThrow(() -> new UsernameNotFoundException("user with email " + email + "not found"));

        return mapper.mapToDto(user);
    }

}
