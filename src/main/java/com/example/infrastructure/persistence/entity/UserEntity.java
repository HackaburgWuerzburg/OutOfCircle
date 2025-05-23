package com.example.infrastructure.persistence.entity;

import com.example.domain.enums.DifficultyType;
import com.example.domain.enums.TopicType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private DifficultyType difficulty;

    @Column(name = "skip_count_today")
    private int skipCountToday;

    @Column(name = "reward_given_today")
    private boolean rewardGivenToday;

    @Column(name = "coin")
    private int coin;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic")
    private TopicType topic;

    public UserEntity() {
    }

    public UserEntity(String email, String password, String username, DifficultyType difficulty, TopicType topic) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.difficulty = difficulty;
        this.skipCountToday = 2;
        this.rewardGivenToday = false;
        this.coin = 0;
        this.topic = topic;
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

    public TopicType getTopic() {
        return topic;
    }

    public void setTopic(TopicType topic) {
        this.topic = topic;
    }
}
