package com.example.application.dto;

import com.example.domain.enums.DifficultyType;
import com.example.domain.enums.TopicType;

public class UserInput {
    private String email;
    private String password;
    private String username;
    private DifficultyType difficulty;
    //private TopicType topic;

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
