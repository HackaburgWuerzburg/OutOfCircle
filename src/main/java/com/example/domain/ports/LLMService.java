package com.example.domain.ports;

public interface LLMService {
    /**
     * Generates a motivational or behavior-breaking daily goal based on the user's pattern summary.
     *
     * @param userPattern Summary of user's avoidance or comfort zone behavior
     * @return Suggested daily challenge
     */
    String generateDailyChallenge(String userPattern);
}
