package com.example.domain.ports;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    Optional<Challenge> findChallengeById(Long id);
    List<Challenge> findChallengesByUserId(Long userId);
    List<Challenge> findChallengesByTopic(String topic);
    List<Challenge> findChallengesByDate(LocalDate date);
    List<Challenge> findChallengesByDifficulty(DifficultyType difficulty);
    List<Challenge> findChallengesByCompletionStatus(boolean isCompleted);
    List<Challenge> findAllChallenges();

    //mutation
    Challenge createChallenge(Challenge challenge);
    Challenge updateChallenge(Long id, Challenge challenge);
    boolean deleteChallenge(Long id);
}
