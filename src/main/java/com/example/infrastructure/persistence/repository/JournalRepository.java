package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
}
