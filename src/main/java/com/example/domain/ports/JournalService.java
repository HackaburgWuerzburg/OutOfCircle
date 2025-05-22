package com.example.domain.ports;

import com.example.domain.models.Journal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JournalService {
    Optional<Journal> findJournalById(Long id);
    List<Journal> findJournalByUserId(Long userId);
    Optional<Journal> findJournalByDate(Date date);
    List<Journal> findAllJournals();

    //mutation
    Journal createJournal(Journal journal);
    Journal updateJournal(Long id, Journal journal);
    Boolean deleteJournal(Long id);


}
