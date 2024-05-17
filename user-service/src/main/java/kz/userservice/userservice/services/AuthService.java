package kz.userservice.userservice.services;

import kz.userservice.userservice.models.entities.UserEntity;

public interface AuthService {

    UserEntity currentUser();

    Long currentUserId();
}
