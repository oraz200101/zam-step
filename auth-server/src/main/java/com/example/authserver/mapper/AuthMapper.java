package com.example.authserver.mapper;

import com.example.authserver.models.dtos.UserRegistrationKafka;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.models.entities.AuthEntity;
import com.example.authserver.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
@RequiredArgsConstructor
public abstract class AuthMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @BeforeMapping
    protected void setUp(@MappingTarget AuthEntity entity, UserRegistrationRequest request) {
        entity.setRoles(Set.of(Role.USER));
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    @BeforeMapping
    protected void setUp(@MappingTarget UserRegistrationKafka kafka, AuthEntity entity, UserRegistrationRequest request) {
        kafka.setId(entity.getId());
        kafka.setPassword(entity.getPassword());
    }

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public abstract AuthEntity mapToEntity(UserRegistrationRequest request);

    @Mapping(target = "firstname", source = "request.firstname")
    @Mapping(target = "lastname", source = "request.lastname")
    @Mapping(target = "patronymic", source = "request.patronymic")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "birthDate", source = "request.birthDate")
    @Mapping(target = "password", ignore = true)
    public abstract UserRegistrationKafka mapToKafka(AuthEntity entity, UserRegistrationRequest request);
}

