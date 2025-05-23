package com.example.domain.ports;

import com.example.domain.models.Journal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JournalService {
    Optional<Journal> findJournalById(Long id);
    List<Journal> findJournalByUserId(Long userId);
    Optional<Journal> findJournalByDate(LocalDate date);
    List<Journal> findAllJournals();
    Optional<Journal> findLatestJournalByUserId(Long userId);

    //mutation
    Journal createJournal(Journal journal);
    Journal updateJournal(Long id, Journal journal);
    Boolean deleteJournal(Long id);


}
