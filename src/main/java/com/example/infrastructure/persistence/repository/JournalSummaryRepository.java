package com.example.infrastructure.persistence.repository;

import com.example.domain.models.JournalSummary;
import com.example.infrastructure.persistence.entity.JournalSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalSummaryRepository extends JpaRepository<JournalSummaryEntity, Long> {
}
