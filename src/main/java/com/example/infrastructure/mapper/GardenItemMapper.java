package com.example.infrastructure.mapper;

import com.example.application.dto.GardenItemInput;
import com.example.domain.models.GardenItem;
import com.example.infrastructure.persistence.entity.GardenItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GardenItemMapper {
    GardenItem entityToDomain(GardenItemEntity entity);
    GardenItemEntity domainToEntity(GardenItem gardenItem);

    GardenItem inputToDomain(GardenItemInput input);
}
