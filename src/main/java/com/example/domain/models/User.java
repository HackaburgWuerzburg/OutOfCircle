package com.example.domain.models;

import com.example.domain.enums.DifficultyType;

public class User {
    private Long id;
    private String email;
    private String password;
    private String username;
    private DifficultyType difficulty;
    private int skipCountToday;
    private boolean rewardGivenToday;
    private int coin;

    public User() {
    }

    public User(Long id, String email, String password, String username, DifficultyType difficulty) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.difficulty = difficulty;
        this.skipCountToday = 2;
        this.rewardGivenToday = false;
        this.coin = 0;
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

    public int getSkipCountToday() {
        return skipCountToday;
    }

    public void setSkipCountToday(int skipCountToday) {
        this.skipCountToday = skipCountToday;
    }

    public boolean isRewardGivenToday() {
        return rewardGivenToday;
    }

    public void setRewardGivenToday(boolean rewardGivenToday) {
        this.rewardGivenToday = rewardGivenToday;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
