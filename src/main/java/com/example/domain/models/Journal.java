package com.example.domain.models;

import java.util.Date;

public class Journal {
    private Long id;
    private Long userId;
    private Date date;
    private String content;

    public Journal() {
    }

    public Journal(Long id, Long userId, Date date, String content) {
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
}
