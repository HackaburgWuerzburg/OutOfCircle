package com.example.infrastructure.persistence.service;

import com.example.domain.models.Journal;
import com.example.domain.ports.JournalService;
import com.example.infrastructure.mapper.JournalMapper;
import com.example.infrastructure.persistence.entity.JournalEntity;
import com.example.infrastructure.persistence.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    private final JournalMapper journalMapper;

    public JournalServiceImpl(JournalRepository journalRepository, JournalMapper journalMapper) {
        this.journalRepository = journalRepository;
        this.journalMapper = journalMapper;
    }

    @Override
    public Optional<Journal> findLatestJournalByUserId(Long userId) {
        return journalRepository.findLatestJournalByUserId(userId)
                .map(journalMapper::entityToDomain);
    }

    @Override
    public Optional<Journal> findJournalById(Long id) {
        return journalRepository.findById(id)
                .map(journalMapper::entityToDomain);
    }

    @Override
    public List<Journal> findJournalByUserId(Long userId) {
        return journalRepository.findByUserId(userId)
                .stream()
                .map(journalMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Journal> findJournalByDate(LocalDate date) {
        return journalRepository.findByDate(date)
                .map(journalMapper::entityToDomain);
    }

    @Override
    public List<Journal> findAllJournals() {
        return journalRepository.findAll()
                .stream()
                .map(journalMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Journal createJournal(Journal journal) {
        JournalEntity savedJournalEntity = journalRepository.save(journalMapper.domainToEntity(journal));
        return journalMapper.entityToDomain(savedJournalEntity);
    }

    @Override
    public Journal updateJournal(Long id, Journal journal) {
        JournalEntity updatedJournalEntity = journalMapper.domainToEntity(journal);
        updatedJournalEntity.setId(id);
        return journalMapper.entityToDomain(journalRepository.save(updatedJournalEntity));
    }

    @Override
    public Boolean deleteJournal(Long id) {
        if(journalRepository.existsById(id)) {
            journalRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
