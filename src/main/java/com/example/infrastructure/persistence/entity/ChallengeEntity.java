package com.example.infrastructure.persistence.entity;

import com.example.domain.enums.DifficultyType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "challenges")
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private DifficultyType difficulty;

    @Column(nullable = false)
    private boolean isCompleted;

    public ChallengeEntity() {
    }

    public ChallengeEntity(Long userId, String topic, Date date, String content, DifficultyType difficulty, boolean isCompleted) {
        this.userId = userId;
        this.topic = topic;
        this.date = date;
        this.content = content;
        this.difficulty = difficulty;
        this.isCompleted = isCompleted;
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
