package com.example.infrastructure.persistence.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.domain.models.Journal;
import com.example.infrastructure.persistence.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
    Optional<JournalEntity> findByDate(LocalDate date);
    List<JournalEntity> findByUserId(Long userId);

    @Query(value = "SELECT * FROM journals WHERE user_id = :userId ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Optional<JournalEntity> findLatestJournalByUserId(@Param("userId") Long userId);


}
