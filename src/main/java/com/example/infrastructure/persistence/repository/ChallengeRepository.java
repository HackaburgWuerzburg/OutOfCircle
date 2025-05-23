package com.example.infrastructure.persistence.repository;

import com.example.domain.enums.DifficultyType;
import com.example.domain.models.Challenge;
import com.example.infrastructure.persistence.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
    List<ChallengeEntity> findByUserId(Long userId);
    List<ChallengeEntity> findByTopic(String topic);
    List<ChallengeEntity> findByDifficulty(DifficultyType difficulty);
    List<ChallengeEntity> findByCompletedIs(boolean isCompleted);
    List<ChallengeEntity> findByDate(LocalDate date);
}
