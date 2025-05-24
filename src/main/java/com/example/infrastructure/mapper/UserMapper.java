package com.example.infrastructure.mapper;

import com.example.application.dto.UserInput;
import com.example.domain.models.User;
import com.example.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User user);

    User inputToDomain(UserInput input);
}
