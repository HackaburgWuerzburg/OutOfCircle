package com.example.infrastructure.persistence.service;

import com.example.domain.enums.MoodTag;
import com.example.domain.models.JournalSummary;
import com.example.domain.ports.JournalSummaryService;
import com.example.infrastructure.mapper.JournalSummaryMapper;
import com.example.infrastructure.persistence.entity.JournalSummaryEntity;
import com.example.infrastructure.persistence.repository.JournalSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalSummaryServiceImpl implements JournalSummaryService {
    private final JournalSummaryRepository journalSummaryRepository;
    private final JournalSummaryMapper journalSummaryMapper;

    public JournalSummaryServiceImpl(JournalSummaryRepository journalSummaryRepository, JournalSummaryMapper journalSummaryMapper) {
        this.journalSummaryRepository = journalSummaryRepository;
        this.journalSummaryMapper = journalSummaryMapper;
    }

    @Override
    public Optional<JournalSummary> findJournalSummaryById(Long id) {
        return journalSummaryRepository.findById(id)
                .map(journalSummaryMapper::entityToDomain);
    }

    @Override
    public List<JournalSummary> findJournalSummaryByUserId(Long userId) {
        return journalSummaryRepository.findByUserId(userId)
                .stream()
                .map(journalSummaryMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<JournalSummary> findJournalSummaryByJournalId(Long journalId) {
        return journalSummaryRepository.findByJournalId(journalId)
                .map(journalSummaryMapper::entityToDomain);
    }

    @Override
    public Optional<JournalSummary> findJournalSummaryByDate(Date date) {
        return journalSummaryRepository.findByDate(date)
                .map(journalSummaryMapper::entityToDomain);
    }

    @Override
    public List<JournalSummary> findJournalSummaryByMoodTag(MoodTag moodTag) {
        return journalSummaryRepository.findByMoodTag(moodTag)
                .stream()
                .map(journalSummaryMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<JournalSummary> findAllJournalSummary() {
        return journalSummaryRepository.findAll()
                .stream()
                .map(journalSummaryMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public JournalSummary createJournalSummary(JournalSummary journalSummary) {
        JournalSummaryEntity savedJournalSummaryEntity = journalSummaryRepository.save(journalSummaryMapper.domainToEntity(journalSummary));
        return journalSummaryMapper.entityToDomain(savedJournalSummaryEntity);
    }

    @Override
    public JournalSummary updateJournalSummary(Long id, JournalSummary journalSummary) {
        JournalSummaryEntity updatedJournalSummaryEntity = journalSummaryMapper.domainToEntity(journalSummary);
        updatedJournalSummaryEntity.setId(id);
        return journalSummaryMapper.entityToDomain(journalSummaryRepository.save(updatedJournalSummaryEntity));
    }

    @Override
    public boolean deleteJournalSummary(Long id) {
        if(journalSummaryRepository.existsById(id)) {
            journalSummaryRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
