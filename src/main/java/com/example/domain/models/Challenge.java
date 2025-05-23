package com.example.domain.models;

import com.example.domain.enums.DifficultyType;

import java.time.LocalDate;
import java.util.Date;

public class Challenge {
    private Long id;
    private Long userId;
    private String topic;
    private LocalDate date;
    private String content;
    private DifficultyType difficulty;
    private boolean isCompleted;

    public Challenge() {
    }

    public Challenge(Long id, Long userId, String topic, LocalDate date, String content, DifficultyType difficulty, boolean isCompleted) {
        this.id = id;
        this.userId = userId;
        this.topic = topic;
        this.date = date;
        this.content = content;
        this.difficulty = difficulty;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
