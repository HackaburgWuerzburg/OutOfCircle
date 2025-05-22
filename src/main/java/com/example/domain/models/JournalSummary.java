package com.example.domain.models;

import com.example.domain.enums.MoodTag;

import java.util.Date;

public class JournalSummary {
    private Long id;
    private Long journalId;
    private Long userId;
    private Date date;
    private String summary;
    private MoodTag moodTag;

    public JournalSummary() {
    }

    public JournalSummary(Long id, Long journalId, Long userId, Date date, String summary, MoodTag moodTag) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
