package com.example.application.controllers;

import com.example.domain.enums.DifficultyType;
import com.example.domain.enums.MoodTag;
import com.example.infrastructure.persistence.entity.*;
import com.example.infrastructure.persistence.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/devtools")
public class DatabaseController {

    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final JournalSummaryRepository journalSummaryRepository;
    private final GardenItemRepository gardenItemRepository;
    private final GardenRepository gardenRepository;
    private final ChallengeRepository challengeRepository;

    public DatabaseController(UserRepository userRepository, JournalRepository journalRepository, JournalSummaryRepository journalSummaryRepository, GardenItemRepository gardenItemRepository, GardenRepository gardenRepository, ChallengeRepository challengeRepository) {
        this.userRepository = userRepository;
        this.journalRepository = journalRepository;
        this.journalSummaryRepository = journalSummaryRepository;
        this.gardenItemRepository = gardenItemRepository;
        this.gardenRepository = gardenRepository;
        this.challengeRepository = challengeRepository;
    }

    @DeleteMapping("/delete-all")
    public String deleteAll() {
        userRepository.deleteAll();
        journalRepository.deleteAll();
        journalSummaryRepository.deleteAll();
        gardenItemRepository.deleteAll();
        gardenRepository.deleteAll();
        challengeRepository.deleteAll();

        return "Database deleted";
    }

    @PostMapping("/fill-database")
    public ResponseEntity<String> fillDatabase() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity("alice@example.com", "123456", "alice", DifficultyType.EASY, null));
        users.add(new UserEntity("bob@example.com", "654321", "bob", DifficultyType.HARD, null));
        userRepository.saveAll(users);

        List<JournalEntity> journals = new ArrayList<>();
        journals.add(new JournalEntity(users.get(0).getId(), LocalDate.of(2025, 5, 22), "Today was sunny."));
        journals.add(new JournalEntity(users.get(0).getId(), LocalDate.of(2025, 5, 23), "Learned Spring Boot."));
        journalRepository.saveAll(journals);

        List<JournalSummaryEntity> summaries = new ArrayList<>();
        summaries.add(new JournalSummaryEntity(journals.get(0).getId(), users.get(0).getId(), LocalDate.of(2025, 5, 22), "Feeling good today", MoodTag.HAPPY));
        summaries.add(new JournalSummaryEntity(journals.get(1).getId(), users.get(0).getId(), LocalDate.of(2025, 5, 23), "Productive learning day", MoodTag.SAD));
        journalSummaryRepository.saveAll(summaries);

        // 1. Gardens
        GardenEntity garden1 = new GardenEntity(users.get(0).getId(), new ArrayList<>());
        GardenEntity garden2 = new GardenEntity(users.get(1).getId(), new ArrayList<>());
        gardenRepository.saveAll(List.of(garden1, garden2));

        // 2. Garden Items
        List<GardenItemEntity> gardenItems = new ArrayList<>();
        gardenItems.add(new GardenItemEntity(garden1.getId()));
        gardenItems.add(new GardenItemEntity(garden2.getId()));
        gardenItemRepository.saveAll(gardenItems);

        // (opsiyonel) Gardens'a item listesi ekle
        garden1.setItems(new ArrayList<>(List.of(gardenItems.get(0), gardenItems.get(1))));
        garden2.setItems(new ArrayList<>(List.of(gardenItems.get(1))));
        gardenRepository.saveAll(List.of(garden1, garden2));


        List<ChallengeEntity> challenges = new ArrayList<>();
        challenges.add(new ChallengeEntity(users.get(0).getId(), "social", LocalDate.of(2025, 5, 23), "Do bodyweight training", DifficultyType.MEDIUM, true));
        challenges.add(new ChallengeEntity(users.get(1).getId(), "academic", LocalDate.of(2025, 5, 23), "Do homework", DifficultyType.MEDIUM, true));
        challengeRepository.saveAll(challenges);

        return ResponseEntity.ok("\u2705 Mock veriler basariyla yuklendi.");
    }
}
