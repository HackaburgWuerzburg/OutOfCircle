package com.example.domain.models;

import java.time.LocalDate;
import java.util.Date;

public class Journal {
    private Long id;
    private Long userId;
    private LocalDate date;
    private String content;

    public Journal() {
    }

    public Journal(Long id, Long userId, LocalDate date, String content) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.content = content;
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
}
