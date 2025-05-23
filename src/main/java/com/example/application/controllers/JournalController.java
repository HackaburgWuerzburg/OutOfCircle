package com.example.application.controllers;

import com.example.application.dto.JournalInput;
import com.example.domain.models.Journal;
import com.example.infrastructure.mapper.JournalMapper;
import com.example.infrastructure.persistence.service.JournalServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    private final JournalServiceImpl journalService;
    private final JournalMapper journalMapper;

    public JournalController(JournalServiceImpl journalService, JournalMapper journalMapper) {
        this.journalService = journalService;
        this.journalMapper = journalMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        return journalService.findJournalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Journal>> getJournalsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(journalService.findJournalByUserId(userId));
    }

    @GetMapping("/date")
    public ResponseEntity<Journal> getJournalByDate(@RequestParam("date") LocalDate date) {
        Optional<Journal> journal = journalService.findJournalByDate(date);
        return journal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals() {
        return ResponseEntity.ok(journalService.findAllJournals());
    }

    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody JournalInput journalInput) {
        Journal createdJournal = journalService.createJournal(journalMapper.inputToDomain(journalInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJournal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable Long id, @RequestBody JournalInput journalInput) {
        Journal updatedJournal = journalService.updateJournal(id, journalMapper.inputToDomain(journalInput));
        return ResponseEntity.ok(updatedJournal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        boolean deleted = journalService.deleteJournal(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
