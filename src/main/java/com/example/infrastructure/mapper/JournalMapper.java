package com.example.infrastructure.mapper;

import com.example.application.dto.JournalInput;
import com.example.domain.models.Journal;
import com.example.infrastructure.persistence.entity.JournalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalMapper {
    Journal entityToDomain(JournalEntity entity);
    JournalEntity domainToEntity(Journal journal);

    Journal inputToDomain(JournalInput input);

}
