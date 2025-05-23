package com.example.infrastructure.persistence.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.domain.enums.MoodTag;
import com.example.domain.models.JournalSummary;
import com.example.infrastructure.persistence.entity.JournalSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JournalSummaryRepository extends JpaRepository<JournalSummaryEntity, Long> {
    List<JournalSummaryEntity> findByUserId(Long userId);
    Optional<JournalSummaryEntity> findByJournalId(Long journalId);
    Optional<JournalSummaryEntity> findByDate(Date date);
    List<JournalSummaryEntity> findByMoodTag(MoodTag moodTag);
}
