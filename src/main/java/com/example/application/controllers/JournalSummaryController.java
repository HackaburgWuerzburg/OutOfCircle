package com.example.application.controllers;

import com.example.application.dto.JournalSummaryInput;
import com.example.domain.enums.MoodTag;
import com.example.domain.models.JournalSummary;
import com.example.infrastructure.mapper.JournalSummaryMapper;
import com.example.infrastructure.persistence.service.JournalSummaryServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/journal-summaries")
public class JournalSummaryController {

    private final JournalSummaryServiceImpl journalSummaryService;
    private final JournalSummaryMapper journalSummaryMapper;

    public JournalSummaryController(JournalSummaryServiceImpl journalSummaryService, JournalSummaryMapper journalSummaryMapper) {
        this.journalSummaryService = journalSummaryService;
        this.journalSummaryMapper = journalSummaryMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalSummary> getJournalSummaryById(@PathVariable Long id) {
        return journalSummaryService.findJournalSummaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JournalSummary>> getJournalSummariesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(journalSummaryService.findJournalSummaryByUserId(userId));
    }

    @GetMapping("/journal/{journalId}")
    public ResponseEntity<JournalSummary> getJournalSummaryByJournalId(@PathVariable Long journalId) {
        return journalSummaryService.findJournalSummaryByJournalId(journalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date")
    public ResponseEntity<JournalSummary> getJournalSummaryByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return journalSummaryService.findJournalSummaryByDate(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mood/{moodTag}")
    public ResponseEntity<List<JournalSummary>> getJournalSummariesByMoodTag(@PathVariable MoodTag moodTag) {
        return ResponseEntity.ok(journalSummaryService.findJournalSummaryByMoodTag(moodTag));
    }

    @GetMapping
    public ResponseEntity<List<JournalSummary>> getAllJournalSummaries() {
        return ResponseEntity.ok(journalSummaryService.findAllJournalSummary());
    }

    @PostMapping
    public ResponseEntity<JournalSummary> createJournalSummary(@RequestBody JournalSummaryInput journalSummaryInput) {
        JournalSummary created = journalSummaryService.createJournalSummary(
                journalSummaryMapper.inputToDomain(journalSummaryInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalSummary> updateJournalSummary(@PathVariable Long id,
                                                               @RequestBody JournalSummaryInput journalSummaryInput) {
        JournalSummary updated = journalSummaryService.updateJournalSummary(id,
                journalSummaryMapper.inputToDomain(journalSummaryInput));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournalSummary(@PathVariable Long id) {
        boolean deleted = journalSummaryService.deleteJournalSummary(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
