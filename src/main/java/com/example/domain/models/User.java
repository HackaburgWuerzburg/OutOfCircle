package com.example.domain.models;

import com.example.domain.enums.DifficultyType;

public class User {
    private Long id;
    private String email;
    private String password;
    private String username;
    private DifficultyType difficulty;

    public User() {
    }

    public User(Long id, String email, String password, String username, DifficultyType difficulty) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }
}
