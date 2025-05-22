package com.example.infrastructure.mapper;


import com.example.application.dto.JournalSummaryInput;
import com.example.domain.models.JournalSummary;
import com.example.infrastructure.persistence.entity.JournalSummaryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalSummaryMapper {
    JournalSummary entityToDomain(JournalSummaryEntity entity);
    JournalSummaryEntity domainToEntity(JournalSummary journalSummary);

    JournalSummary inputToDomain(JournalSummaryInput input);
}
