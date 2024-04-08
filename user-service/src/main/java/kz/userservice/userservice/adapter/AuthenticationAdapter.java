package kz.userservice.userservice.adapter;

import kz.userservice.userservice.models.entities.UserEntity;

public interface AuthenticationAdapter {

    UserEntity currentUser();

    Long currentUserId();
}
