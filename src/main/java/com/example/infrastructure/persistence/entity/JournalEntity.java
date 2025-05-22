package com.example.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "journals")
public class JournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String content;

    public JournalEntity() {
    }

    public JournalEntity(Long userId, Date date, String content) {
        this.userId = userId;
        this.date = date;
        this.content = content;
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
