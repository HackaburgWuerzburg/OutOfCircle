package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.GardenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<GardenEntity, Long> {
}
