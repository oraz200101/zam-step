package kz.userservice.userservice.mapper;


import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.models.dtos.UserToAuthService;
import kz.userservice.userservice.models.entities.UserEntity;
import kz.userservice.userservice.models.enums.Role;
import kz.userservice.userservice.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
@RequiredArgsConstructor
public abstract class UserMapper {
    @BeforeMapping
    protected void setUp(@MappingTarget UserEntity entity, UserRegistrationDto dto) {
        entity.setRoles(Set.of(Role.USER));
        entity.setBirthDate(DateUtil.parseToLocalDateTime(dto.getBirthDate()));
    }

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    public abstract UserEntity mapToEntity(UserRegistrationDto userRegistrationRequest);

    public abstract UserToAuthService mapToDto(UserEntity user);
}
