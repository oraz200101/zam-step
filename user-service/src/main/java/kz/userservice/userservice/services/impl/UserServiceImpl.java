package kz.userservice.userservice.services.impl;

import kz.userservice.userservice.mapper.UserMapper;
import kz.userservice.userservice.models.dtos.UserRegistrationRequest;
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
    public void createUser(UserRegistrationRequest registrationRequest) {
        repository.save(mapper.mapToEntity(registrationRequest));
    }

    @Override
    public UserToAuthService toAuthService(Long id) {
        UserEntity user = repository.findById(id)
                                    .orElseThrow(() -> new UsernameNotFoundException("user with id " + id + "not found"));

        return mapper.mapToDto(user);
    }

}
