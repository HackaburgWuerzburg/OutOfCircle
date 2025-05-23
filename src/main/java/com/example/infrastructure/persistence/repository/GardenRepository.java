package com.example.infrastructure.persistence.repository;

import com.example.domain.models.Garden;
import com.example.infrastructure.persistence.entity.GardenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GardenRepository extends JpaRepository<GardenEntity, Long> {
    Optional<GardenEntity> findByUserId(Long userId);
}
