package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.GardenItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenItemRepository extends JpaRepository<GardenItemEntity, Long> {
    List<GardenItemEntity> findByGardenId(Long gardenId);
}
