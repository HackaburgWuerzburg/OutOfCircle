package com.example.application.controllers;

import com.example.application.dto.ChallengeInput;
import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;
import com.example.infrastructure.mapper.ChallengeMapper;
import com.example.infrastructure.persistence.service.ChallengeServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeServiceImpl challengeService;
    private final ChallengeMapper challengeMapper;

    public ChallengeController(ChallengeServiceImpl challengeService, ChallengeMapper challengeMapper) {
        this.challengeService = challengeService;
        this.challengeMapper = challengeMapper;
    }

    @GetMapping("/generate/{userId}")
    public ResponseEntity<?> generateNewChallenge(@PathVariable Long userId) {
        try {
            String challenge = challengeService.generateAndSaveChallenge(userId);
            return ResponseEntity.ok(challenge);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or journal not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Challenge generation failed: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {
        return challengeService.findChallengeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Challenge>> getChallengesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(challengeService.findChallengesByUserId(userId));
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<Challenge>> getChallengesByTopic(@PathVariable String topic) {
        return ResponseEntity.ok(challengeService.findChallengesByTopic(topic));
    }

    @GetMapping("/date")
    public ResponseEntity<List<Challenge>> getChallengesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(challengeService.findChallengesByDate(date));
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Challenge>> getChallengesByDifficulty(@PathVariable DifficultyType difficulty) {
        return ResponseEntity.ok(challengeService.findChallengesByDifficulty(difficulty));
    }

    @GetMapping("/completed/{isCompleted}")
    public ResponseEntity<List<Challenge>> getChallengesByCompletionStatus(@PathVariable boolean isCompleted) {
        return ResponseEntity.ok(challengeService.findChallengesByCompletionStatus(isCompleted));
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return ResponseEntity.ok(challengeService.findAllChallenges());
    }

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody ChallengeInput challengeInput) {
        Challenge createdChallenge = challengeService.createChallenge(challengeMapper.inputToDomain(challengeInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody ChallengeInput challengeInput) {
        Challenge updatedChallenge = challengeService.updateChallenge(id, challengeMapper.inputToDomain(challengeInput));
        return ResponseEntity.ok(updatedChallenge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        boolean deleted = challengeService.deleteChallenge(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
