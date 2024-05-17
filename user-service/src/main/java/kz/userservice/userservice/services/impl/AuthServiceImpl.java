package kz.userservice.userservice.services.impl;

import kz.userservice.userservice.execptions.UserNotFoundException;
import kz.userservice.userservice.models.entities.UserEntity;
import kz.userservice.userservice.repository.UserRepository;
import kz.userservice.userservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;

    @Override
    public UserEntity currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return repository.findByEmail(authentication.getName())
                                    .orElseThrow(() -> new UserNotFoundException("user was not found"));
    }

    @Override
    public Long currentUserId() {
        return repository.getUserId(currentUser().getEmail());
    }
}
