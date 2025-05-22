package com.example.application.dto;

import com.example.domain.enums.MoodTag;

import java.util.Date;

public class JournalSummaryInput {
    private Long journalId;
    private Long userId;
    private Date date;
    private String summary;
    private MoodTag moodTag;

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
