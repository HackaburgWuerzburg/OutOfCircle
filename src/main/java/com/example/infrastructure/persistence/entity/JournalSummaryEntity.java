package com.example.infrastructure.persistence.entity;

import com.example.domain.enums.MoodTag;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "journal_summaries")
public class JournalSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long journalId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private MoodTag moodTag;

    public JournalSummaryEntity() {
    }

    public JournalSummaryEntity(Long journalId, Long userId, LocalDate date, String summary, MoodTag moodTag) {
        this.journalId = journalId;
        this.userId = userId;
        this.date = date;
        this.summary = summary;
        this.moodTag = moodTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public MoodTag getMoodTag() {
        return moodTag;
    }

    public void setMoodTag(MoodTag moodTag) {
        this.moodTag = moodTag;
    }
}
