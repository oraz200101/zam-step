package com.example.authserver.utils.validation;

import com.example.authserver.exception.ValidationException;
import com.example.authserver.models.dtos.UserRegistrationRequest;

public class UserValidator {

    public static void validate(UserRegistrationRequest request){
        if (request == null){
            throw new ValidationException("UserRegistrationRequest expected not to be null");
        }

        if (request.getEmail() == null || request.getEmail().isEmpty()){
            throw new ValidationException("user email expected not to be null or empty");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()){
            throw new ValidationException("user password expected not to be null or empty");
        }

        if (request.getPassword().length() < 7){
            throw new ValidationException("user password expected has minimum 7 symbols");
        }

        if (request.getFirstname() == null || request.getFirstname().isEmpty()){
            throw new ValidationException("user firstname expected not to be null or empty");
        }

        if (request.getLastname() == null || request.getLastname().isEmpty()){
            throw new ValidationException("user lastname expected not to be null or empty");
        }
    }

}
