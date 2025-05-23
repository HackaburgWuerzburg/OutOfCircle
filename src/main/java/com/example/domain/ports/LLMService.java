package com.example.domain.ports;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;

import java.util.List;

public interface LLMService {
    /**
     * Generates a motivational or behavior-breaking daily goal based on the user's pattern summary.
     *
     * @param userPattern Summary of user's avoidance or comfort zone behavior
     * @return Suggested daily challenge
     */
    String generateDailyChallenge(String userPattern);
    String generateChallengeFromTopicAndDifficulty(List<Challenge> challenges, String topic, DifficultyType difficulty);
    String detectTopicFromJournal(String journalText);

}
