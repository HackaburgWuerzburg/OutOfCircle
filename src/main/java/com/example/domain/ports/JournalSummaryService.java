package com.example.domain.ports;

import com.example.domain.enums.MoodTag;
import com.example.domain.models.JournalSummary;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JournalSummaryService {
    Optional<JournalSummary> findJournalSummaryById(Long id);
    List<JournalSummary> findJournalSummaryByUserId(Long userId);
    Optional<JournalSummary> findJournalSummaryByJournalId(Long journalId);
    Optional<JournalSummary> findJournalSummaryByDate(Date date);
    List<JournalSummary> findJournalSummaryByMoodTag(MoodTag moodTag);
    List<JournalSummary> findAllJournalSummary();

    //mutation
    JournalSummary createJournalSummary(JournalSummary journalSummary);
    JournalSummary updateJournalSummary(Long id, JournalSummary journalSummary);
    boolean deleteJournalSummary(Long id);
}
