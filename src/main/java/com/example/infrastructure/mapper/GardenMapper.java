package com.example.infrastructure.mapper;

import com.example.application.dto.GardenInput;
import com.example.domain.models.Garden;
import com.example.infrastructure.persistence.entity.GardenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GardenMapper {
    Garden entityToDomain(GardenEntity entity);
    GardenEntity DomainToEntity(Garden garden);

    Garden inputToDomain(GardenInput input);
}
