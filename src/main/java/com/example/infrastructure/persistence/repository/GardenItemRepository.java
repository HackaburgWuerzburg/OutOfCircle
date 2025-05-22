package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.GardenItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenItemRepository extends JpaRepository<GardenItemEntity, Long> {
}
