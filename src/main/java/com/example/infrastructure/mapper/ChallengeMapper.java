package com.example.infrastructure.mapper;

import com.example.application.dto.ChallengeInput;
import com.example.domain.models.Challenge;
import com.example.infrastructure.persistence.entity.ChallengeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChallengeMapper {
    Challenge entityToDomain(ChallengeEntity entity);
    ChallengeEntity domainToEntity(Challenge challenge);

    Challenge inputToDomain(ChallengeInput input);
}
