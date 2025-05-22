package com.example.application.dto;

import com.example.domain.enums.DifficultyType;

import java.util.Date;

public class ChallengeInput {
    private Long userId;
    private String topic;
    private Date date;
    private String content;
    private DifficultyType difficulty;
    private boolean isCompleted;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
