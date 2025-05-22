package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
}
